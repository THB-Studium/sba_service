package com.example.demo.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Ausleihe;

public interface AusleiheRepository
        extends JpaRepository<Ausleihe, UUID>, JpaSpecificationExecutor<Ausleihe> {

    public Ausleihe findOneByUserBenutzernummerAndBuchSignatur(long benutzernummer, String signatur);
    public Ausleihe findOneByBuchSignatur(String signatur);  
    
    public Set<Ausleihe> findAllByUserId(UUID userId);
    
    public int countByBuchIsbn(String isbn13);
}
