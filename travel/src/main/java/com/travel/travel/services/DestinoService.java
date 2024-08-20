package com.travel.travel.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travel.models.Destino;
import com.travel.travel.repositories.DestinoRepository;

@Service 
public class DestinoService {
    
    private final DestinoRepository destinoRepository;

    public DestinoService(DestinoRepository destinoRepository){
        this.destinoRepository = destinoRepository;
    }

        public ResponseEntity<Object> addDestino(Destino destino){
            destinoRepository.save(destino);
            return new ResponseEntity<>(destino, HttpStatus.CREATED);
        }
    }
