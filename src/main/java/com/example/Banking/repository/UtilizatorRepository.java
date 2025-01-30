/** Clasa pentru gestionarea operațiunilor de acces la date legate de entitatea Utilizator
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */

package com.example.Banking.repository;

import com.example.Banking.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizatorRepository extends JpaRepository<Utilizator, Integer> {
    Utilizator findByEmail(String email);
    Utilizator findByCnp(String cnp);
    Optional<Utilizator> findByTelefon(String telefon);

    Optional<Utilizator> findOptionalByEmail(String email);



}