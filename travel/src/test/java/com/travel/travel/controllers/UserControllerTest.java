package com.travel.travel.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.travel.travel.config.security.JWTAuthtenticationConfig;
import com.travel.travel.exception.HappyTravelException;
import com.travel.travel.models.entity.LoginRequest;
import com.travel.travel.models.entity.User;
import com.travel.travel.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController; 

    @Mock
    private UserService userService; 

    @Mock
    private LoginRequest loginRequest; 

    @Mock
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Mock
    private User mockUser;

    String name = "Kat";
    String email = "kat@kat.com";
    String password = "1Qazxsw*";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser.setName(name);
        mockUser.setEmail(email);
        mockUser.setPassword(password);
    }

    @Test
    void testSignIn() throws HappyTravelException {

        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                "El usuario se ha registrado con exito!",
                HttpStatus.CREATED
        );

        when(userService.addNewUser(mockUser)).thenReturn(expectedResponse);

        ResponseEntity<?> response = userController.signIn(mockUser);

        verify(userService).addNewUser(mockUser);
        assertEquals(expectedResponse, response);
    }
}
