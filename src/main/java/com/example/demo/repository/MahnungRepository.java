package com.example.demo.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Mahnung;

public interface MahnungRepository
        extends JpaRepository<Mahnung, UUID>, JpaSpecificationExecutor<Mahnung> {
    
    public Mahnung findOneByBuchSignatur(String signatur);
    public Set<Mahnung> findAllByUserId(UUID userId);
}
