/** Clasa pentru gestionarea operațiunilor legate de cardurile utilizatorilor, cum ar fi
 * adăugarea, ștergerea, resetarea PIN-ului, efectuarea de tranzacții (depunere/retragere),
 * precum și expunerea de API-uri pentru tipurile de carduri și monede disponibile.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */



package com.example.Banking.controller;

import com.example.Banking.model.Card;
import com.example.Banking.model.Utilizator;
import com.example.Banking.service.CardService;
import com.example.Banking.service.TranzactieService;
import com.example.Banking.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/carduri")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private UtilizatorService utilizatorService;

    @Autowired
    private TranzactieService tranzactieService;

    @GetMapping
    public String getCarduri(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<Card> carduri = cardService.getCarduriByEmail(email);
        model.addAttribute("carduri", carduri);

        return "carduri";
    }

    @GetMapping("/adauga")
    public String showAddCardForm(Model model) {
        model.addAttribute("card", new Card());
        return "adauga-card";
    }

    @PostMapping("/adauga")
    public String adaugaCard(@ModelAttribute Card card) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Găsește utilizatorul conectat
        Utilizator utilizator = utilizatorService.getUtilizatorByEmail(email);
        if (utilizator == null) {
            throw new IllegalStateException("Utilizatorul conectat nu a fost găsit!");
        }

        // Setează utilizatorul pentru card
        card.setUtilizator(utilizator);

        // Verifică dacă TipCard este null
        if (card.getTipCard() == null) {
            throw new IllegalArgumentException("Tipul cardului trebuie să fie selectat!");
        }

        // Setează valorile generate automat
        card.setNumarCard(cardService.generateRandomNumber());
        card.setIban(cardService.generateRandomIban());
        card.setCvv(cardService.generateRandomCvv());
        card.setPin(cardService.generateRandomPin());
        card.setSold(BigDecimal.ZERO);

        // Setează moneda implicită dacă nu este specificată
        if (card.getMoneda() == null) {
            card.setMoneda(Card.Moneda.lei);
        }

        // Salvează cardul
        cardService.adaugaCard(card);
        return "redirect:/carduri";
    }


    @PostMapping("/adauga-bani/{id}")
    public String adaugaBani(@PathVariable Integer id, @RequestParam BigDecimal suma) {
        cardService.adaugaBani(id, suma);

        // Adaugă tranzacția de tip "depunere"
        tranzactieService.adaugaTranzactie("depunere", suma, "Adăugare bani", id);

        return "redirect:/carduri";
    }

    @PostMapping("/retrage-bani/{id}")
    public String retrageBani(@PathVariable Integer id, @RequestParam BigDecimal suma) {
        cardService.retrageBani(id, suma);

        // Adaugă tranzacția de tip "retragere"
        tranzactieService.adaugaTranzactie("retragere", suma, "Retragere bani", id);

        return "redirect:/carduri";
    }


    @PostMapping("/sterge/{id}")
    public String stergeCard(@PathVariable("id") Integer id) {
        cardService.stergeCard(id);
        return "redirect:/carduri";
    }

    @PostMapping("/reseteaza-pin/{id}")
    public String reseteazaPin(@PathVariable("id") Integer id, @RequestParam("pin") String pinNou) {
        cardService.reseteazaPin(id, pinNou);
        return "redirect:/carduri";
    }

    @GetMapping("/api/tipuri-card")
    @ResponseBody
    public List<Card.TipCard> getTipuriCard() {
        return Arrays.asList(Card.TipCard.values());
    }

    @GetMapping("/api/monede")
    @ResponseBody
    public List<Card.Moneda> getMonede() {
        return Arrays.asList(Card.Moneda.values());
    }


    @GetMapping("/cauta")
    public String cautaCard(@RequestParam("numarCard") String numarCard, Model model, Authentication authentication) {
        String email = authentication.getName();
        List<Card> carduriUtilizator = cardService.findByUserEmail(email);

        List<Card> rezultate = carduriUtilizator.stream()
                .filter(card -> card.getNumarCard().contains(numarCard))
                .toList();

        if (rezultate.isEmpty()) {
            model.addAttribute("error", "Cardul nu a fost găsit.");
        } else {
            model.addAttribute("carduri", rezultate);
        }
        return "carduri";
    }



}
