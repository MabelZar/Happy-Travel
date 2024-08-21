package com.travel.travel.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travel.models.Destination;
import com.travel.travel.repositories.DestinationRepository;

@Service 
public class DestinationService {
    
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository){
        this.destinationRepository = destinationRepository;
    }

        public ResponseEntity<Object> addNewDestination(Destination destination){
            destinationRepository.save(destination);
            return new ResponseEntity<>(destination, HttpStatus.CREATED);
        }

        public ResponseEntity<Object> updateDestination(Destination destination){
            destinationRepository.save(destination);
            return new ResponseEntity<>("edited correctly",HttpStatus.OK);
        }
    }
