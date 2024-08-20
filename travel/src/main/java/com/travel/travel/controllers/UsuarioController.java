package com.travel.travel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travel.models.Usuario;
import com.travel.travel.services.UsuarioService;

@RestController
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;}
    
    @PostMapping("/")
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario){
       return usuarioService.addUsuario(usuario);
    }    
}
