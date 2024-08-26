package com.travel.travel.services;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.travel.travel.models.User;
import com.travel.travel.repositories.UserRepository;

@Service
public class CustomDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con este Email" + email));
            
        return new org.springframework.security.core.userdetails.User(
        user.getEmail(), 
        user.getPassword(), 
        new ArrayList<>() 
        );

    }
}
