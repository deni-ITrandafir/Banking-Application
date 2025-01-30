/** Clasa pentru gestionarea autentificării și înregistrării utilizatorilor
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.controller;

import com.example.Banking.model.Utilizator;
import com.example.Banking.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;

@Controller
public class AuthController {

    @Autowired
    private UtilizatorService utilizatorService;

    // Pagina de login
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Email sau parola greșite!");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "Te-ai delogat cu succes.");
        }
        return "login"; // Returnează fișierul login.html
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("utilizator", new Utilizator());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Utilizator utilizator,
                               @RequestParam("dataNasterii") String dataNasterii,
                               Model model) {
        try {
            // Conversia manuală a datei
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            utilizator.setDataNasterii(dateFormat.parse(dataNasterii));

            utilizatorService.inregistreazaUtilizator(utilizator);
            model.addAttribute("successMessage", "Cont creat cu succes!");
            return "redirect:/login?registered=true"; // Redirect cu flag pentru mesajul de succes
        } catch (Exception e) {
            model.addAttribute("errorMessage", "A apărut o eroare: " + e.getMessage());
            return "register";
        }
    }



}
