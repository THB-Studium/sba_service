package com.example.demo.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Favory;

public interface FavoryRepository
        extends JpaRepository<Favory, UUID>, JpaSpecificationExecutor<Favory> {

    public Favory findOneByUserId(UUID userId); 
    public Set<Favory> findAllByUserId(UUID userId);
}
