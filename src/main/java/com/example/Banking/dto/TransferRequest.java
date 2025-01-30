/** Clasa pentru gestionarea unui obiect de tip DTO (Data Transfer Object) utilizat pentru
 *  a transfera informațiile necesare în procesul de inițiere a unui transfer bancar.
 *  Comunicarea datelor de transfer între partea de front-end (formularul de transfer) și partea de back-end
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.dto;

import java.math.BigDecimal;

public class TransferRequest {

    private Integer sourceCardId;
    private String targetIban;
    private BigDecimal amount;
    private String description;
    private String pin;

    // Constructor fără parametri
    public TransferRequest() {
    }

    // Constructor cu toți parametrii
    public TransferRequest(Integer sourceCardId, String targetIban, BigDecimal amount, String description, String pin) {
        this.sourceCardId = sourceCardId;
        this.targetIban = targetIban;
        this.amount = amount;
        this.description = description;
        this.pin = pin;
    }

    // Getteri și setteri
    public Integer getSourceCardId() {
        return sourceCardId;
    }

    public void setSourceCardId(Integer sourceCardId) {
        this.sourceCardId = sourceCardId;
    }

    public String getTargetIban() {
        return targetIban;
    }

    public void setTargetIban(String targetIban) {
        this.targetIban = targetIban;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
