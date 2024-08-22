package com.travel.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travel.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    boolean existsByEmail(String email);
}
