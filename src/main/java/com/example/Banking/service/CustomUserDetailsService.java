/** Clasa pentru încărcarea detaliilor utilizatorului în timpul autentificării, bazat pe adresa de email.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.service;

import com.example.Banking.model.Utilizator;
import com.example.Banking.repository.UtilizatorRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilizatorRepository utilizatorRepository;

    public CustomUserDetailsService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilizator utilizator = utilizatorRepository.findByEmail(email);
        if (utilizator == null) {
            throw new UsernameNotFoundException("Email-ul nu a fost găsit: " + email);
        }

        return User.builder()
                .username(utilizator.getEmail())
                .password(utilizator.getParola())
                .roles("USER")
                .build();
    }

}


