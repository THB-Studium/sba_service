package com.example.demo.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Reservierung;

public interface ReservierungRepository
        extends JpaRepository<Reservierung, UUID>, JpaSpecificationExecutor<Reservierung> {

    public Reservierung findOneByBuchSignatur(String signatur);
    public Set<Reservierung> findAllByUserId(UUID userId);
}
