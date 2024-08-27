package com.travel.travel.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.travel.travel.models.Role;
import com.travel.travel.repositories.RoleRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private RoleRepository roleRepository;

    public DataLoader (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       if(roleRepository.count() == 0) {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
       }
    }

    
}
