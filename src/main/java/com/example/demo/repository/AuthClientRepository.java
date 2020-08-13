package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AuthClientDetails;

@Repository
public interface AuthClientRepository extends JpaRepository<AuthClientDetails, String> {

    Optional<AuthClientDetails> findByClientId(String clientId);

    List<AuthClientDetails> findAllByClientId(String clientId);
}
