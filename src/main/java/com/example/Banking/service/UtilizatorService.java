/** Clasa pentru gestionarea utilizatorilor, cum ar fi înregistrarea, actualizarea
 * profilului și obtinerea detaliilor utilizatorilor pe baza email-ului.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.service;

import com.example.Banking.model.Utilizator;
import com.example.Banking.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UtilizatorService {

    private final UtilizatorRepository utilizatorRepository;


    public UtilizatorService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    public Utilizator getUtilizatorByEmail(String email) {
        return utilizatorRepository.findByEmail(email);
    }

    public void inregistreazaUtilizator(Utilizator utilizator) {
        // Verificăm dacă email-ul sau CNP-ul sunt deja folosite
        if (utilizatorRepository.findByEmail(utilizator.getEmail()) != null) {
            throw new IllegalArgumentException("Email-ul este deja utilizat.");
        }
        if (utilizatorRepository.findByCnp(utilizator.getCnp()) != null) {
            throw new IllegalArgumentException("CNP-ul este deja utilizat.");
        }

        // Salvăm utilizatorul în baza de date
        utilizatorRepository.save(utilizator);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utilizator findByEmail(String email) {
        // Folosește metoda cu `Optional`
        return utilizatorRepository.findOptionalByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit!"));
    }

    public void updateProfil(Utilizator utilizatorActualizat, String parolaNoua, String email) {
        // Găsește utilizatorul existent în baza de date
        Utilizator utilizatorExistent = findByEmail(email);

        // Actualizează câmpurile non-opționale
        utilizatorExistent.setNume(utilizatorActualizat.getNume());
        utilizatorExistent.setPrenume(utilizatorActualizat.getPrenume());
        utilizatorExistent.setEmail(utilizatorActualizat.getEmail());
        utilizatorExistent.setTelefon(utilizatorActualizat.getTelefon());
        utilizatorExistent.setOras(utilizatorActualizat.getOras());
        utilizatorExistent.setJudet(utilizatorActualizat.getJudet());
        utilizatorExistent.setStrada(utilizatorActualizat.getStrada());
        utilizatorExistent.setNumar(utilizatorActualizat.getNumar());
        utilizatorExistent.setCnp(utilizatorActualizat.getCnp());
        utilizatorExistent.setDataNasterii(utilizatorActualizat.getDataNasterii());
        utilizatorExistent.setGen(utilizatorActualizat.getGen());

        // Verifică și actualizează parola doar dacă este completată
        if (parolaNoua != null && !parolaNoua.isEmpty()) {
            utilizatorExistent.setParola(passwordEncoder.encode(parolaNoua));
        }

        // Salvează utilizatorul actualizat în baza de date
        utilizatorRepository.save(utilizatorExistent);
    }



}
