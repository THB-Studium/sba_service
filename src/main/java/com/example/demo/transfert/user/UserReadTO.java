package com.example.demo.transfert.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.demo.model.Role;

public class UserReadTO {
    private UUID id;

    private String benutzernummer;

    private boolean isActive;

    private Set<Role> roles = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserReadTO id(UUID id) {
        this.id = id;
        return this;
    }

    public String getBenutzernummer() {
        return benutzernummer;
    }

    public void setBenutzernummer(String benutzernummer) {
        this.benutzernummer = benutzernummer;
    }

    public UserReadTO benutzernummer(String benutzernummer) {
        this.benutzernummer = benutzernummer;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public UserReadTO isActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserReadTO roles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

}
