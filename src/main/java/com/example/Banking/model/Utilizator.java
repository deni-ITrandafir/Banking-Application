/** Clasa pentru reprezentarea și gestionarea utilizatorilor.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */



package com.example.Banking.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "utilizatori")
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Utilizator")
    private Integer idUtilizator;

    @Column(name = "Nume", nullable = false)
    private String nume;

    @Column(name = "Prenume", nullable = false)
    private String prenume;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Parola", nullable = false)
    private String parola;

    @Column(name = "Telefon", unique = true)
    private String telefon;

    @Column(name = "Oras")
    private String oras;

    @Column(name = "Judet")
    private String judet;

    @Column(name = "Strada")
    private String strada;

    @Column(name = "Numar")
    private String numar;

    @Enumerated(EnumType.STRING)
    @Convert(converter = Gen.GenConverter.class)
    @Column(name = "Gen")
    private Gen gen;

    @Column(name = "CNP", unique = true, nullable = false)
    private String cnp;

    @Temporal(TemporalType.DATE)
    @Column(name = "DataNasterii", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNasterii;

    @OneToMany(mappedBy = "utilizator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> carduri;

    @OneToMany(mappedBy = "utilizator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CerereImprumut> imprumuturi;

    public Integer getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Integer idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    public Gen getGen() {
        return gen;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public List<Card> getCarduri() {
        return carduri;
    }

    public void setCarduri(List<Card> carduri) {
        this.carduri = carduri;
    }

    public List<CerereImprumut> getImprumuturi() {
        return imprumuturi;
    }

    public void setImprumuturi(List<CerereImprumut> imprumuturi) {
        this.imprumuturi = imprumuturi;
    }


    public enum Gen {
        masculin, feminin;

        @Converter(autoApply = true)
        public static class GenConverter implements AttributeConverter<Gen, String> {
            @Override
            public String convertToDatabaseColumn(Gen gen) {
                return gen == null ? null : gen.name().toLowerCase(); // Salvează în baza de date cu litere mici
            }

            @Override
            public Gen convertToEntityAttribute(String dbData) {
                if (dbData == null) {
                    return null;
                }
                return Gen.valueOf(dbData.toUpperCase()); // Transformă valorile în enum
            }
        }
    }


}
