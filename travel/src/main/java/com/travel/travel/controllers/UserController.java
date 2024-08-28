package com.travel.travel.controllers;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.travel.travel.exception.HappyTravelException;
import com.travel.travel.models.LoginRequest;
import com.travel.travel.models.User;
import com.travel.travel.security.JWTAuthtenticationConfig;
import com.travel.travel.services.UserService;
import io.jsonwebtoken.io.IOException;

import static com.travel.travel.security.ConstansSecurity.*;

@RestController
public class UserController {

    JWTAuthtenticationConfig jwtAuthtenticationConfig = new JWTAuthtenticationConfig();

    UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @PostMapping(LOGIN_URL)
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
            throw new IOException("FAIL Authentication");
        }

        return finalUser;
    }

     @PostMapping(SIGNIN_URL)
    public ResponseEntity<?> signIn(@RequestBody User user) throws HappyTravelException{
        return userService.addNewUser(user);
    }
}
