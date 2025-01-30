/** Clasa pentru afișarea istoricului tranzacțiilor pentru utilizatorul autentificat
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.controller;

import com.example.Banking.model.Tranzactie;
import com.example.Banking.service.TranzactieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/istoric-tranzactii")
public class TranzactieController {

    @Autowired

    private TranzactieService tranzactieService;

    @GetMapping
    public String getTranzactii(Model model, Authentication authentication) {
        String email = authentication.getName(); // Email-ul utilizatorului conectat
        List<Tranzactie> tranzactii = tranzactieService.getTranzactiiByUserEmail(email);
        model.addAttribute("tranzactii", tranzactii);
        return "istoric-tranzactii";
    }
}


