package com.travel.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travel.models.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer>{}
