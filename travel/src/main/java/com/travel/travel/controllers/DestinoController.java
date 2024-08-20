package com.travel.travel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travel.models.Destino;
import com.travel.travel.services.DestinoService;

@RestController
public class DestinoController {

    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService){
        this.destinoService = destinoService;}

        @PostMapping("/destinos")
        public ResponseEntity<Object> addDestino(@RequestBody Destino destino) {
            return destinoService.addDestino(destino);
        }
    }
