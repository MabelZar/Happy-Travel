package com.travel.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travel.models.Destino;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Integer>{}
