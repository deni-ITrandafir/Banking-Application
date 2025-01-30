/** Clasa pentru reprezentarea și gestionarea cardurilor bancare asociate utilizatorilor.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "carduri")

public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Card")
    private Integer idCard;

    @Column(name = "NumarCard", unique = true, nullable = false)
    private String numarCard;

    @Column(name = "TipCard", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipCard tipCard;

    @Column(name = "Moneda", nullable = false)
    @Enumerated(EnumType.STRING)
    private Moneda moneda;

    @Column(name = "Sold", precision = 15, scale = 2, nullable = false)
    private BigDecimal sold;

    @Column(name = "Pin", nullable = false)
    private String pin;

    @Column(name = "IBAN", unique = true, nullable = false)
    private String iban;


    @Column(name = "CVV", nullable = false, length = 3)
    private String cvv;


    @ManyToOne
    @JoinColumn(name = "ID_Utilizator", nullable = false)
    private Utilizator utilizator;


    // Getteri și setteri

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getSold() {
        return sold;
    }

    public void setSold(BigDecimal sold) {
        this.sold = sold;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public TipCard getTipCard() {
        return tipCard;
    }

    public void setTipCard(TipCard tipCard) {
        this.tipCard = tipCard;
    }

    public String getNumarCard() {
        return numarCard;
    }

    public void setNumarCard(String numarCard) {
        this.numarCard = numarCard;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    public enum Moneda {
        lei, euro, lire, dolari
    }

    public enum TipCard {
        economii, cumparaturi


    }
}
