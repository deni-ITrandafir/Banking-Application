/** Clasa pentru gestionarea tranzacțiilor bancare, afișarea istoricului și
 * efectuarea transferurilor de fonduri.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.service;
import com.example.Banking.dto.TransferRequest;
import com.example.Banking.exception.InsufficientFundsException;
import com.example.Banking.exception.InvalidCurrencyException;
import com.example.Banking.exception.InvalidPinException;



import com.example.Banking.model.Card;
import com.example.Banking.model.Tranzactie;
import com.example.Banking.model.Utilizator;
import com.example.Banking.repository.CardRepository;
import com.example.Banking.repository.TranzactieRepository;
import com.example.Banking.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TranzactieService {

    UtilizatorRepository utilizatorRepository;

    @Autowired
    private TranzactieRepository tranzactieRepository;

    @Autowired
    private CardRepository cardRepository;


    public List<Tranzactie> getTranzactiiByUserEmail(String email) {
        // Găsește tranzacțiile direct pe baza email-ului utilizatorului
        return tranzactieRepository.findByUtilizatorEmail(email);
    }


    public void adaugaTranzactie(String tipTranzactie, BigDecimal suma, String descriere, Integer idCard) {
        Tranzactie tranzactie = new Tranzactie();
        tranzactie.setTipTranzactie(Tranzactie.TipTranzactie.valueOf(tipTranzactie.toLowerCase())); // Conversie la litere mici
        tranzactie.setSuma(suma);
        tranzactie.setDescriere(descriere);
        tranzactie.setDataTranzactie(new Date());

        Card card = cardRepository.findById(idCard).orElseThrow(() -> new RuntimeException("Card inexistent"));
        tranzactie.setCard(card);

        tranzactieRepository.save(tranzactie);
    }


    public void transferFunds(TransferRequest transferRequest, String email)
            throws InsufficientFundsException, InvalidCurrencyException, InvalidPinException {
        Card sourceCard = cardRepository.findById(transferRequest.getSourceCardId())
                .orElseThrow(() -> new RuntimeException("Cardul sursă nu a fost găsit."));

        Card targetCard = cardRepository.findByIban(transferRequest.getTargetIban())
                .orElseThrow(() -> new RuntimeException("Cardul destinatar nu a fost găsit."));


        // Check PIN
        if (!sourceCard.getPin().equals(transferRequest.getPin())) {
            throw new InvalidPinException("PIN incorect.");
        }

        // Check currency
        if (!sourceCard.getMoneda().equals(targetCard.getMoneda())) {
            throw new InvalidCurrencyException("Moneda trebuie să fie aceeași.");
        }

        // Check funds
        if (sourceCard.getSold().compareTo(transferRequest.getAmount()) < 0) {
            throw new InsufficientFundsException("Fonduri insuficiente.");
        }

        // Process transfer
        sourceCard.setSold(sourceCard.getSold().subtract(transferRequest.getAmount()));
        targetCard.setSold(targetCard.getSold().add(transferRequest.getAmount()));

        cardRepository.save(sourceCard);
        cardRepository.save(targetCard);

        // Log transaction
        Tranzactie sourceTransaction = new Tranzactie();
        sourceTransaction.setCard(sourceCard);
        sourceTransaction.setTipTranzactie(Tranzactie.TipTranzactie.transfer);
        sourceTransaction.setSuma(transferRequest.getAmount());
        sourceTransaction.setDescriere("Transfer către IBAN " + targetCard.getIban());
        sourceTransaction.setDataTranzactie(new Date());
        tranzactieRepository.save(sourceTransaction);

        Tranzactie targetTransaction = new Tranzactie();
        targetTransaction.setCard(targetCard);
        targetTransaction.setTipTranzactie(Tranzactie.TipTranzactie.transfer);
        targetTransaction.setSuma(transferRequest.getAmount());
        targetTransaction.setDescriere("Transfer de la IBAN " + sourceCard.getIban());
        targetTransaction.setDataTranzactie(new Date());
        tranzactieRepository.save(targetTransaction);
    }

}
