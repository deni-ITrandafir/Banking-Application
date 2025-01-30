/** Clasa pentru gestionarea operațiunilor legate de entitatea Card, cum ar fi adăugarea,
 * ștergerea, actualizarea și generarea atributelor cardului.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.service;

import com.example.Banking.model.Card;
import com.example.Banking.model.Utilizator;
import com.example.Banking.repository.CardRepository;
import com.example.Banking.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    private static final Random RANDOM = new Random();

    public List<Card> getCarduriByEmail(String email) {
        Utilizator utilizator = utilizatorRepository.findByEmail(email);
        return cardRepository.findByUtilizator(utilizator);
    }

    public List<Card> findByUserEmail(String email) {
        return cardRepository.findByUserEmail(email);
    }

    public void adaugaCard(Card card) {
        if (card.getPin() == null || card.getPin().isEmpty()) {
            card.setPin(generateRandomPin()); // Generează un PIN valid dacă lipsește
        }
        cardRepository.save(card);
    }


    public void adaugaBani(Integer idCard, BigDecimal suma) {
        Card card = cardRepository.findById(idCard).orElseThrow(() -> new RuntimeException("Card inexistent"));
        card.setSold(card.getSold().add(suma));
        cardRepository.save(card);
    }

    public void retrageBani(Integer idCard, BigDecimal suma) {
        Card card = cardRepository.findById(idCard).orElseThrow(() -> new RuntimeException("Card inexistent"));
        if (card.getSold().compareTo(suma) < 0) {
            throw new RuntimeException("Sold insuficient");
        }
        card.setSold(card.getSold().subtract(suma));
        cardRepository.save(card);
    }

    public void stergeCard(Integer id) {
        cardRepository.deleteById(id);
    }

    public void reseteazaPin(Integer id, String pinNou) {
        Card card = cardRepository.findById(id).orElseThrow();
        card.setPin(pinNou);
        cardRepository.save(card);
    }

    public String generateRandomIban() {
        String countryCode = "RO";
        int checksum = RANDOM.nextInt(90) + 10;
        StringBuilder iban = new StringBuilder(countryCode + checksum);
        for (int i = 0; i < 16; i++) {
            iban.append(RANDOM.nextInt(10));
        }
        return iban.toString();
    }

    public String generateRandomCvv() {
        int cvv = RANDOM.nextInt(900) + 100;
        return String.valueOf(cvv);
    }

    public String generateRandomNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(RANDOM.nextInt(10));
        }
        return cardNumber.toString();
    }

    public String generateRandomPin() {
        int pin = RANDOM.nextInt(9000) + 1000; // Generează un număr între 1000 și 9999
        return String.valueOf(pin);
    }


}
