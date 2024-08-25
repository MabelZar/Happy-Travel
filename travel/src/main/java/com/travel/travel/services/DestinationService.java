package com.travel.travel.services;

import java.util.Optional;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.travel.travel.exception.DuplicatedDestinationException;
import com.travel.travel.exception.HappyTravelException;
import com.travel.travel.exception.InvalidDataException;
import com.travel.travel.models.Destination;
import com.travel.travel.models.User;
import com.travel.travel.repositories.DestinationRepository;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }
    // ADDNEWDESTINATION TENDRÁ CONFLICTOS YA QUE TUVE QUE CAMBIARLA PARA MANEJAR LA EXCEPCIÓN
    public void addNewDestination(Destination destination) {

        // Validación de que el título no sea nulo o esté vacío
    if (destination.getTitle() == null || destination.getTitle().isEmpty()) {
        throw new InvalidDataException("El título del destino no puede estar vacío.");
    }

    // Validación de que la ubicación no sea nula o esté vacía (si también es requerida)
    if (destination.getLocation() == null || destination.getLocation().isEmpty()) {
        throw new InvalidDataException("La ubicación del destino no puede estar vacía.");
    }

        User user = destination.getUser();

        Optional<Destination> existingDestination = destinationRepository.findByTitleAndLocationAndUser(
                destination.getTitle(),
                destination.getLocation(),
                user);

        if (existingDestination.isPresent()) {
            throw new DuplicatedDestinationException("El destino con el mismo título y ubicación ya existe.");
        }
        destinationRepository.save(destination);
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

    // public List<Destination> getLocation() {
    // return destinationRepository.findAll();
    // }este es el original
    public List<Destination> getLocation() throws HappyTravelException {

        List<Destination> destinationList = destinationRepository.findAll();
        if (destinationList == null || destinationList.size() == 0) {
            throw new HappyTravelException("destination table is empty");
        }
        return destinationList;
    
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