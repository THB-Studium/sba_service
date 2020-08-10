package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class User implements Serializable, UserDetails {

    // ATTRIBUTES:
    private static final long serialVersionUID = -1859485293362766653L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String vorname;
    private String nachname;
    private String email;
    @NotNull
    private String benutzernummer;
    @NotNull
    @JsonIgnore
    private String kennwort;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "adresseId")
    private Adresse adresse;
    @Transient
    private Set<? extends GrantedAuthority> authorities = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    // KONSTRUKTOREN:

    public User(String vorname, String nachname, String email, String benutzernummer, String kennwort, Adresse adresse) {
        super();
        this.setVorname(vorname);
        this.setNachname(nachname);
        this.setEmail(email);
        this.setBenutzernummer(benutzernummer);
        this.setKennwort(kennwort);
        this.setAdresse(adresse);
    }

    public User(String benutzernummer, String kennwort) {
        this(null, null, null, null, null, null);
    }

    public User(User user) {
        this.benutzernummer = user.getBenutzernummer();
        this.kennwort = user.getKennwort();

        Set<GrantedAuthority> auths = new HashSet<>();
        for (Role role : user.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }

        this.authorities = auths;
    }

    public User() {
        this(null, null);
    }

    // SETTER & GETTER:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBenutzernummer() {
        return benutzernummer;
    }

    public void setBenutzernummer(String benutzernummer) {
        this.benutzernummer = benutzernummer;
    }

    public String getKennwort() {
        return kennwort;
    }

    public void setKennwort(String kennwort) {
        this.kennwort = kennwort;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return kennwort;
    }

    @Override
    public String getUsername() {
        return benutzernummer;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
