package com.travel.travel.mapper;

import com.travel.travel.models.dto.DestinationDTO;
import com.travel.travel.models.dto.UserDTO;
import com.travel.travel.models.entity.Destination;
import com.travel.travel.models.entity.User;

public class EntityToDTOMapper {
    
    public static UserDTO convertToUserDTO(User user) {
        return new UserDTO(user.getId());
    }

    public static DestinationDTO convertToDestinationDTO(Destination destination) {
        UserDTO userDTO = convertToUserDTO(destination.getUser());
        return new DestinationDTO(
            destination.getId(),
            destination.getTitle(),
            destination.getLocation(),
            destination.getDescription(),
            destination.getUrl_image(),
            userDTO
        );
    }

    public static Destination convertToDestinationEntity(DestinationDTO destinationDTO, User user) {
        Destination destination = new Destination();
        destination.setId(destinationDTO.getId());
        destination.setTitle(destinationDTO.getTitle());
        destination.setLocation(destinationDTO.getLocation());
        destination.setDescription(destinationDTO.getDescription());
        destination.setUrl_image(destinationDTO.getUrlImage());
        destination.setUser(user);
        return destination;
    }
}

