package com.travel.travel.controllers;

import static com.travel.travel.config.security.ConstansSecurity.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.travel.travel.exception.HappyTravelException;
import com.travel.travel.mapper.EntityToDTOMapper;
import com.travel.travel.models.dto.DestinationDTO;
import com.travel.travel.models.entity.Destination;
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

    @PostMapping(DESTINATIONS_ADD_URL)
    public ResponseEntity<DestinationDTO> addNeWDestination(@RequestBody Destination destination)
            throws HappyTravelException {
        destinationService.addNewDestination(destination);
        DestinationDTO resultDTO = EntityToDTOMapper.convertToDestinationDTO(destination);
        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    }

    @PutMapping(DESTINATIONS_UPDATE_URL)
    public ResponseEntity<Object> updateDestination(@PathVariable int id, @RequestBody Destination destination)
            throws HappyTravelException {
        destination.setId(id); 
        destinationService.updateDestination(destination);
        return ResponseEntity.ok().build();
    }


    @GetMapping(DESTINATIONS_URL)
    public List<DestinationDTO> getLocationPublic() throws HappyTravelException {
        List<Destination> destinations = destinationService.getLocation();
        List<DestinationDTO> destinationsDto = destinations.stream().map(EntityToDTOMapper::convertToDestinationDTO)
                .collect(Collectors.toList());
        return destinationsDto;
    }

    @GetMapping(DESTINATIONS_LOCATION_URL)
    public List<DestinationDTO> getLocation() throws HappyTravelException {
        List<Destination> destinations = destinationService.getLocation();
        List<DestinationDTO> destinationsDto = destinations.stream().map(EntityToDTOMapper::convertToDestinationDTO)
                .collect(Collectors.toList());
        return destinationsDto;
    }

    @DeleteMapping(DESTINATIONS_DELETE_URL)
    public ResponseEntity<Object> deleteDestination(@PathVariable int id) throws HappyTravelException {
        return destinationService.deleteDestination(id);
    }

    @GetMapping(DESTINATIONS_DETAILS_URL)
    public ResponseEntity<Object> getDestinationDetails(@PathVariable int id) throws HappyTravelException {
        try {
            Optional<Destination> destination = destinationService.getDestinationDetails(id);
            if (destination.isPresent()) {
                DestinationDTO destinationDTO = EntityToDTOMapper.convertToDestinationDTO(destination.get());
                return new ResponseEntity<>(destinationDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>("No existe el destino solicitado", HttpStatus.NOT_FOUND);
        } catch (HappyTravelException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
