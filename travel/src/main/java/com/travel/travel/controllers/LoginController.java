package com.travel.travel.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travel.models.LoginRequest;
import com.travel.travel.models.User;
import com.travel.travel.security.JWTAuthtenticationConfig;
import com.travel.travel.services.UserService;

import io.jsonwebtoken.io.IOException;

@RestController
public class LoginController {

    JWTAuthtenticationConfig jwtAuthtenticationConfig = new JWTAuthtenticationConfig();

    UserService userService;

    public LoginController( UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/log_in")
    public User login(
        @RequestBody LoginRequest loginRequest
   ) {
        User finalUser = null;
        Optional<User> user = userService.getUserByEmail(loginRequest.getEmail());
        if(user.isPresent()){
            String token = jwtAuthtenticationConfig.getJWTToken(loginRequest.getEmail());
            finalUser = user.get();
            finalUser.setToken(token);
        } else {
            throw new IOException("FALLO AL AUTHENTICAR");
        }

        return finalUser;
    
    }
}
