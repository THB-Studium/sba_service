package com.example.demo.repository;

import java.util.UUID;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.User;

@Named
public interface UserRepository
	extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

}
