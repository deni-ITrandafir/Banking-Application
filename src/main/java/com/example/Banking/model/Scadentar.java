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
@Getter
@Setter
@Entity
@Table(name = "scadentar")
public class Scadentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Scadentar")
    private Integer idScadentar;

    @ManyToOne
    @JoinColumn(name = "ID_Cerere", nullable = false)
    private CerereImprumut cerereImprumut;

    @Column(name = "SumaRata", precision = 15, scale = 2, nullable = false)
    private BigDecimal sumaRata;

    @Column(name = "Platit", nullable = false)
    private Boolean platit;

    public Integer getIdScadentar() {
        return idScadentar;
    }

    public void setIdScadentar(Integer idScadentar) {
        this.idScadentar = idScadentar;
    }

    public Boolean getPlatit() {
        return platit;
    }

    public void setPlatit(Boolean platit) {
        this.platit = platit;
    }

    public BigDecimal getSumaRata() {
        return sumaRata;
    }

    public void setSumaRata(BigDecimal sumaRata) {
        this.sumaRata = sumaRata;
    }

    public CerereImprumut getCerereImprumut() {
        return cerereImprumut;
    }

    public void setCerereImprumut(CerereImprumut cerereImprumut) {
        this.cerereImprumut = cerereImprumut;
    }

    // Getteri și setteri
}
