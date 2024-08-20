package com.travel.travel.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travel.models.Usuario;
import com.travel.travel.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;

    }

        public ResponseEntity<Object> addUsuario(Usuario usuario){
            usuarioRepository.save(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } 
    }
