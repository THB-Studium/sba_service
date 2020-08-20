package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Adresse;

public interface AdresseRepository
        extends JpaRepository<Adresse, UUID>, JpaSpecificationExecutor<Adresse> {

}
