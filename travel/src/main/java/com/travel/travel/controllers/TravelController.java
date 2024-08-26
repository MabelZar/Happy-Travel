package com.travel.travel.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travel.exception.HappyTravelException;
import com.travel.travel.models.Destination;
import com.travel.travel.models.User;
import com.travel.travel.services.DestinationService;
import com.travel.travel.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TravelController {

    private final DestinationService destinationService;

    private final UserService userService;

    public TravelController(DestinationService destinationService, UserService userService) {
        this.destinationService = destinationService;
        this.userService = userService;
    }

    @PostMapping("/destinations")
    public ResponseEntity<Object> addNeWDestination(@RequestBody Destination destination) throws HappyTravelException {
        destinationService.addNewDestination(destination);
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }

    @PutMapping("/destinations/update")
    public ResponseEntity<Object> updateDestination(@RequestBody Destination destination) throws HappyTravelException {

        destinationService.updateDestination(destination);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/sign_in")
    public ResponseEntity<?> signIn(@RequestBody User user) throws HappyTravelException{

        /* if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required.");
        } */
        return userService.addNewUser(user);
    }

    // @GetMapping("/destinations")
    // public List<Destination> getLocation(){
    // return destinationService.getLocation();
    // }----------------este es el original
    @GetMapping("/destinations")
    public List<Destination> getLocation() throws HappyTravelException {
        return destinationService.getLocation();
    }

    @DeleteMapping("/destinations/{id}")
    public ResponseEntity<Object> deleteDestination(@PathVariable int id) throws HappyTravelException{
        return destinationService.deleteDestination(id);
    }

    @GetMapping("/destinations/details/{id}")
    public ResponseEntity<Object> getDestinationDetails(@PathVariable int id) throws HappyTravelException{
        try {
            Optional<Destination> destination = destinationService.getDestinationDetails(id);
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } catch (HappyTravelException e) {
            // Si se lanza la excepción, devolvemos un 404 con el mensaje de error
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
