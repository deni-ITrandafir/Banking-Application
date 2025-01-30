/** Clasa pentru reprezentarea și gestionarea tranzactiilor bancare asociate utilizatorilor.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tranzactii")
public class Tranzactie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tranzactie")
    private Integer idTranzactie;

    @ManyToOne
    @JoinColumn(name = "ID_Card", nullable = false)
    private Card card;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipTranzactie", nullable = false)
    private TipTranzactie tipTranzactie;

    @Column(name = "Suma", precision = 15, scale = 2, nullable = false)
    private BigDecimal suma;

    @Column(name = "Descriere")
    private String descriere;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DataTranzactie", nullable = false)
    private Date dataTranzactie;

    public Integer getIdTranzactie() {
        return idTranzactie;
    }

    public void setIdTranzactie(Integer idTranzactie) {
        this.idTranzactie = idTranzactie;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public TipTranzactie getTipTranzactie() {
        return tipTranzactie;
    }

    public void setTipTranzactie(TipTranzactie tipTranzactie) {
        this.tipTranzactie = tipTranzactie;
    }

    public BigDecimal getSuma() {
        return suma;
    }

    public void setSuma(BigDecimal suma) {
        this.suma = suma;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Date getDataTranzactie() {
        return dataTranzactie;
    }

    public void setDataTranzactie(Date dataTranzactie) {
        this.dataTranzactie = dataTranzactie;
    }

    public enum TipTranzactie {
        depunere, retragere, transfer
    }

}
