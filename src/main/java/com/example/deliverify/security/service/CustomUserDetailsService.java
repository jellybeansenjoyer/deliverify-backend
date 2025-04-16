package com.example.deliverify.security.service;

import com.example.deliverify.entity.User;
import com.example.deliverify.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

//Sends HTTP 401 Unauthorized status.
//
//Called automatically by Spring Security when:
//
//No token is provided
//
//Token is invalid or expired
//
//User tries to access a protected resource without proper authentication
//
//🛡️ Think of this as the "gatekeeper" that says, “You're not allowed in until you provide valid credentials.”
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Convert the single enum role to GrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(authority) // list with a single role
        );
    }
}
