package com.travel.travel.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travel.models.User;
import com.travel.travel.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

        public ResponseEntity<Object> addNewUser(User user){
            if(userRepository.existsByEmail(user.getEmail())) {
                return new ResponseEntity<>("Este usuario ya se encuentra está registrado", HttpStatus.CREATED);
            }

            userRepository.save(user);
            return new ResponseEntity<>("El usuario se ha registrado con exito!", HttpStatus.CREATED);
        } 
    }
