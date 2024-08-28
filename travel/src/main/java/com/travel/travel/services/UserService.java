package com.travel.travel.services;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.travel.travel.exception.HappyTravelException;
import com.travel.travel.models.entity.Role;
import com.travel.travel.models.entity.User;
import com.travel.travel.repositories.RoleRepository;
import com.travel.travel.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

        public ResponseEntity<Object> addNewUser(User user) throws HappyTravelException{
            if(userRepository.existsByEmail(user.getEmail())) {
                throw new HappyTravelException("No se registró, porque el email ya está siendo utilizado.", HttpStatus.CONFLICT);
            }

        User savedUser = userRepository.save(user);

        
        Role defaultRole = roleRepository.findByName("USER")
        .orElseThrow(() -> new RuntimeException("Default role not found"));

        savedUser.getRoles().add(defaultRole);
        userRepository.save(savedUser);
        return new ResponseEntity<>("El usuario se ha registrado con exito!", HttpStatus.CREATED);
    } 

    public User getUserById (int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getUserByEmail (String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername(); 
            Optional<User> requestedUser = getUserByEmail(email);
            if (!requestedUser.isPresent()) {
                throw new RuntimeException("Usuario no autenticado");
            } 
            return requestedUser;
        } else {
            throw new RuntimeException("Usuario no autenticado");
        }
    }

}
