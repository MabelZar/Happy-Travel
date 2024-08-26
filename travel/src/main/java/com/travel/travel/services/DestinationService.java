package com.travel.travel.services;

import java.util.Optional;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.travel.travel.exception.HappyTravelException;
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

    public void updateDestination(Destination destination) throws HappyTravelException {

        try {
            Optional<Destination> existingDestination = destinationRepository.findById(destination.getId());
        
            if (!existingDestination.isPresent()) {
                // Si el destino no existe, lanzamos una excepción personalizada
                throw new HappyTravelException("El destino con ID " + destination.getId() + " no fue encontrado.");
            }

            destinationRepository.save(destination);
        
        } catch (IllegalArgumentException | OptimisticLockingFailureException | JpaObjectRetrievalFailureException exc) {
            throw new HappyTravelException(exc.getMessage());
        }

    }

    public List<Destination> getLocation() throws HappyTravelException {

        List<Destination> destinationList = destinationRepository.findAll();
        if (destinationList == null || destinationList.size() == 0) {
            throw new HappyTravelException("destination table is empty" );
        }
        return destinationList;
    
    }

    public ResponseEntity<Object> deleteDestination(int id) throws HappyTravelException {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        {
            if (!destinationOptional.isPresent()) {
                throw new HappyTravelException("Este destino no es valido", HttpStatus.CONFLICT);
            }
            destinationRepository.deleteById(id);
            return new ResponseEntity<>("Ha sido eliminado con exito!", HttpStatus.OK);

        }

    }

    public Optional<Destination> getDestinationDetails(int id) {
        return destinationRepository.findById(id);
    }

}
