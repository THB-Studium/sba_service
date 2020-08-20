package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Buch;

public interface BuchRepository
        extends JpaRepository<Buch, UUID>, JpaSpecificationExecutor<Buch> {

    Buch findOneBySignatur(String signatur);
}
