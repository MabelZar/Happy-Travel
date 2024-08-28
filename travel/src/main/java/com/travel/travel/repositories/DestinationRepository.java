package com.travel.travel.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travel.models.entity.Destination;
import com.travel.travel.models.entity.User;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer>{

    Optional<Destination> findByTitleAndLocationAndUser(String title, String location, User user);

}
