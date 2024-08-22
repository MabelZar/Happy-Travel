package com.travel.travel.services;

import java.util.Optional;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travel.models.Destination;
import com.travel.travel.models.User;
import com.travel.travel.repositories.DestinationRepository;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public ResponseEntity<Object> addNewDestination(Destination destination) {

        User user = destination.getUser();

        Optional<Destination> existingDestination = destinationRepository.findByTitleAndLocationAndUser(
                destination.getTitle(),
                destination.getLocation(),
                user);

        if (existingDestination.isPresent()) {
            return new ResponseEntity<>("El destino con el mismo título y ubicación ya existe.", HttpStatus.CONFLICT);
        }
        destinationRepository.save(destination);
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }

    public Optional<Destination> findByTitleAndLocationAndUser(String title, String location, User user) {
        return destinationRepository.findByTitleAndLocationAndUser(title, location, user);
    }

    public ResponseEntity<Object> updateDestination(Destination destination) {
        destinationRepository.save(destination);
        return new ResponseEntity<>("edited correctly", HttpStatus.OK);
    }

    public List<Destination> getLocation() {
        return destinationRepository.findAll();
    }

    public ResponseEntity<Object> deleteDestination(int id) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        {
            if (!destinationOptional.isPresent()) {
                return new ResponseEntity<>("Este destino no es valido", HttpStatus.CONFLICT);
            }
            destinationRepository.deleteById(id);
            return new ResponseEntity<>("El usuario ha sido eliminado con exito!", HttpStatus.OK);

        }

    }

    public Optional<Destination> getDestinationDetails(int id) {
        return destinationRepository.findById(id);
    }

}