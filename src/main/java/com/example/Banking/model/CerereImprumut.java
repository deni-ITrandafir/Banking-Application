/** Clasa pentru gestionarea autentificării și înregistrării utilizatorilor
 * @author Trandafir Denisa Ioana
 * @version 10 Decembrie 2024
 */

package com.example.Banking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "imprumuturi")
public class CerereImprumut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cerere")
    private Integer idCerere;

    @ManyToOne
    @JoinColumn(name = "ID_Utilizator", nullable = false)
    private Utilizator utilizator;

    @Column(name = "Suma", precision = 15, scale = 2, nullable = false)
    private BigDecimal suma;

    @Temporal(TemporalType.DATE)
    @Column(name = "DataPlata", nullable = false)
    private Date dataPlata;

    @Column(name = "Perioada", nullable = false)
    private Integer perioada;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "ID_Card", nullable = false)
    private Card card;

    @OneToMany(mappedBy = "cerereImprumut", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scadentar> scadentar;

    public Integer getIdCerere() {
        return idCerere;
    }

    public void setIdCerere(Integer idCerere) {
        this.idCerere = idCerere;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public BigDecimal getSuma() {
        return suma;
    }

    public void setSuma(BigDecimal suma) {
        this.suma = suma;
    }

    public Date getDataPlata() {
        return dataPlata;
    }

    public void setDataPlata(Date dataPlata) {
        this.dataPlata = dataPlata;
    }

    public Integer getPerioada() {
        return perioada;
    }

    public void setPerioada(Integer perioada) {
        this.perioada = perioada;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Scadentar> getScadentar() {
        return scadentar;
    }

    public void setScadentar(List<Scadentar> scadentar) {
        this.scadentar = scadentar;
    }

    public enum Status {
        IN_ASTEPTARE, ACCEPTATA, REFUZATA
    }

    // Getteri și setteri
}
