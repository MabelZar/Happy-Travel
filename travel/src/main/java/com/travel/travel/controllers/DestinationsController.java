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
import com.travel.travel.services.DestinationService;
import com.travel.travel.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class DestinationsController {

    private final DestinationService destinationService;

    public DestinationsController(DestinationService destinationService, UserService userService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/api/destinations/add")
    public ResponseEntity<Object> addNeWDestination(@RequestBody Destination destination) throws HappyTravelException {
        destinationService.addNewDestination(destination);
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }

    @PutMapping("/api/destinations/update")
    public ResponseEntity<Object> updateDestination(@RequestBody Destination destination) throws HappyTravelException {

        destinationService.updateDestination(destination);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/destinations/location")
    public List<Destination> getLocation() throws HappyTravelException {
        return destinationService.getLocation();
    }

    @DeleteMapping("/api/destinations/delete/{id}")
    public ResponseEntity<Object> deleteDestination(@PathVariable int id) throws HappyTravelException {
        return destinationService.deleteDestination(id);
    }

    @GetMapping("/api/destinations/details/{id}")
    public ResponseEntity<Object> getDestinationDetails(@PathVariable int id) throws HappyTravelException {
        try {
            Optional<Destination> destination = destinationService.getDestinationDetails(id);
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } catch (HappyTravelException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
