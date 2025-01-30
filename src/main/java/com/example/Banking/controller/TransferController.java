/** Clasa pentru funcționalitățile legate de transferurile bancare între conturi
 * Afișarea formularului pentru inițierea unui transfer, populând lista de carduri disponibile pentru utilizatorul autentificat.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.controller;
import com.example.Banking.dto.TransferRequest;
import com.example.Banking.exception.InsufficientFundsException;
import com.example.Banking.exception.InvalidCurrencyException;
import com.example.Banking.exception.InvalidPinException;


import com.example.Banking.model.Card;
import com.example.Banking.service.CardService;
import com.example.Banking.service.TranzactieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transfera-bani")
public class TransferController {

    @Autowired
    private CardService cardService;

    @Autowired
    private TranzactieService tranzactieService;

    @GetMapping
    public String showTransferForm(Model model, Authentication authentication) {
        String email = authentication.getName();
        List<Card> userCards = cardService.findByUserEmail(email);
        model.addAttribute("userCards", userCards);
        model.addAttribute("transferRequest", new TransferRequest());
        return "transfera-bani";
    }

    @PostMapping
    public String processTransfer(@ModelAttribute TransferRequest transferRequest, Authentication authentication, Model model) {
        String email = authentication.getName();

        try {
            tranzactieService.transferFunds(transferRequest, email);
            return "redirect:/istoric-tranzactii";
        } catch (InsufficientFundsException e) {
            model.addAttribute("error", "Fonduri insuficiente.");
        } catch (InvalidCurrencyException e) {
            model.addAttribute("error", "Transferul este permis doar între conturi cu aceeași monedă.");
        } catch (InvalidPinException e) {
            model.addAttribute("error", "PIN incorect.");
        }

        List<Card> userCards = cardService.findByUserEmail(email);
        model.addAttribute("userCards", userCards);
        return "transfera-bani";
    }
}
