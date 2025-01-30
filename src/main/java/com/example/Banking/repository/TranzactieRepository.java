/** Clasa pentru gestionarea operațiunilor de acces la date legate de entitatea Tranzactie.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.repository;


import com.example.Banking.model.Tranzactie;
import com.example.Banking.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TranzactieRepository extends JpaRepository<Tranzactie, Integer> {

    @Query("SELECT t FROM Tranzactie t WHERE t.card.utilizator.email = :email")
    List<Tranzactie> findByUtilizatorEmail(@Param("email") String email);
}
