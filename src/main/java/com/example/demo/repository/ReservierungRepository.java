package com.example.demo.repository;

import java.util.UUID;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Reservierung;

@Named
public interface ReservierungRepository
	extends JpaRepository<Reservierung, UUID>, JpaSpecificationExecutor<Reservierung> {

}
