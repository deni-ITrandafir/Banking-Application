/** Clasa pentru gestionarea funcționalităților legate de profilul utilizatorului autentificat.
 * permite afișarea informațiilor despre profil și actualizarea datelor personale, cum ar fi numele,
 * email-ul, parola sau alte detalii.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */



package com.example.Banking.controller;

import com.example.Banking.model.Utilizator;
import com.example.Banking.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfilController {

    @Autowired
    private UtilizatorService utilizatorService;

    @GetMapping("/contul-meu")
    public String getProfilPage(Authentication authentication, Model model) {
        String email = authentication.getName(); // Obține email-ul utilizatorului autentificat
        Utilizator utilizator = utilizatorService.findByEmail(email);
        model.addAttribute("utilizator", utilizator);
        return "profil";
    }

    @PostMapping("/contul-meu")
    public String updateProfil(Utilizator utilizator, String parolaNoua, Authentication authentication) {
        String email = authentication.getName(); // Obține email-ul utilizatorului autentificat
        utilizatorService.updateProfil(utilizator, parolaNoua, email); // Actualizează profilul
        return "redirect:/contul-meu?success"; // Redirecționează după salvare
    }




}
