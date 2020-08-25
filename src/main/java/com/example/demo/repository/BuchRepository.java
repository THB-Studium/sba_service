package com.example.demo.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Buch;

public interface BuchRepository
        extends JpaRepository<Buch, UUID>, JpaSpecificationExecutor<Buch> {

    public Buch findOneBySignatur(String signatur);
    public Set<Buch> findAllByIsbn(String isbn13);
}
