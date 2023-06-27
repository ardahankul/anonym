package com.example.anonym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.anonym.entity.AppUser;
import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByGuid(String guid);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
}
