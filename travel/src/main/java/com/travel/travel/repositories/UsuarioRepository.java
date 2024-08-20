package com.travel.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travel.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{}
