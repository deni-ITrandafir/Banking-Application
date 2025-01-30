/** Clasa pentru estionarea rutelor principale, redirecționând utilizatorii către
 * pagina de autentificare sau către pagina principală
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.controller;

import com.example.Banking.model.Utilizator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BankController {
    @GetMapping("/index")
    public String showIndexPage() {
        return "index"; // Returnează fișierul index.html din resursele statice
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login"; // Redirecționează către pagina de login
    }

}
