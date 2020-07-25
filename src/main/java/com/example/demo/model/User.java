package com.example.demo.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class User implements Serializable {

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
	private long Benutzernummer;
	@NotNull
	@JsonIgnore
	private String Kennwort;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "adresseId")
	private Adresse adresse;


	
	// KONSTRUKTOREN:

	public User(String vorname, String nachname, String email, long benutzernummer, String kennwort, Adresse adresse) {
		super();
		this.setVorname(vorname);
		this.setNachname(nachname);
		this.setEmail(email);
		this.setBenutzernummer(benutzernummer);
		this.setKennwort(kennwort);
		this.setAdresse(adresse);
	}

	public User(long benutzernummer, String kennwort) {
		this(null, null, null, 0, null, null);
	}

	public User() {
		this(0, null);
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

	public long getBenutzernummer() {
		return Benutzernummer;
	}

	public void setBenutzernummer(long benutzernummer) {
		Benutzernummer = benutzernummer;
	}

	public String getKennwort() {
		return Kennwort;
	}

	public void setKennwort(String kennwort) {
		Kennwort = kennwort;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
}
