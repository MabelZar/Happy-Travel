package com.travel.travel.services;

import java.util.Optional;
import java.util.List;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import com.travel.travel.exception.HappyTravelException;
import com.travel.travel.models.entity.Destination;
import com.travel.travel.models.entity.User;
import com.travel.travel.repositories.DestinationRepository;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final UserService userService;

    public DestinationService(DestinationRepository destinationRepository, UserService userService) {
        this.destinationRepository = destinationRepository;
        this.userService = userService;
    }

    public void addNewDestination(Destination destination) throws HappyTravelException {
    // Validation
    if (destination.getTitle() == null || destination.getTitle().isEmpty()) {
        throw new HappyTravelException("El título del destino no puede estar vacío.", HttpStatus.BAD_REQUEST);
    }

        if (destination.getLocation() == null || destination.getLocation().isEmpty()) {
            throw new HappyTravelException("La ubicación del destino no puede estar vacía.", HttpStatus.BAD_REQUEST);
        }

    // Fetch the user from the database
    User user = destination.getUser();

    if (user == null ||!user.isIdSet()) {
        throw new HappyTravelException("Usuario no especificado.", HttpStatus.BAD_REQUEST);
    }

    User existingUser = userService.getUserById(user.getId());
    if (existingUser == null) {
        throw new HappyTravelException("Usuario no encontrado.", HttpStatus.NOT_FOUND);
    }
    
    // Set the fetched user to the destination
    destination.setUser(existingUser);

    // Check for existing destination
    Optional<Destination> existingDestination = destinationRepository.findByTitleAndLocationAndUser(
            destination.getTitle(),
            destination.getLocation(),
            existingUser);

    if (existingDestination.isPresent()) {
        throw new HappyTravelException("El destino con el mismo título y ubicación ya existe.", HttpStatus.CONFLICT);
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
            throw new HappyTravelException("La tabla destinos esta vacia" );
        }
        return destinationList;
    
    }

    public ResponseEntity<Object> deleteDestination(int id) throws HappyTravelException {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (!destinationOptional.isPresent()) {
            throw new HappyTravelException("Este destino no es valido", HttpStatus.CONFLICT);
        }
        destinationRepository.deleteById(id);
        return new ResponseEntity<>("Ha sido eliminado con exito!", HttpStatus.OK);
    }

    public Optional<Destination> getDestinationDetails(int id) throws HappyTravelException{
        Optional<Destination> destinationDetails = destinationRepository.findById(id);
        if(!destinationDetails.isPresent()){
            throw new HappyTravelException("El id de destino: " + id + " no fue encontrado");
        }
        return destinationDetails;
        
    }

}