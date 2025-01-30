/** Clasa pentru gestionarea operațiunilor de acces la date legate de entitatea Card.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */

package com.example.Banking.repository;


import com.example.Banking.model.Card;
import com.example.Banking.model.CerereImprumut;
import com.example.Banking.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByUtilizator(Utilizator utilizator);

    Optional<Card> findByIban(String iban);

    @Query("SELECT c FROM Card c WHERE c.utilizator.email = :email")
    List<Card> findByUserEmail(@Param("email") String email);

    List<CerereImprumut> findByUtilizator_IdUtilizator(Integer idUtilizator);

}
