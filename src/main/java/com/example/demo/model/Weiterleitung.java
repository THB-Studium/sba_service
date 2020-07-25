package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

@Entity
public class Weiterleitung implements Serializable {

	// ATTRIBUTES:
	private static final long serialVersionUID = -8516974422426875288L;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	@NotNull
	private Date datum;
	
	@NotNull
	@OneToOne
    @MapsId
	private User altBesitzer;
	
	@NotNull
	@OneToOne
    @MapsId
	private User neuBesitzer;	
	
	@NotNull
	@OneToOne
    @MapsId
	private Buch buch;
	
	
	
	// KONSTRUKTOREN:
	
	public Weiterleitung(Date datum, User altBesitzer, User neuBesitzer, Buch buch) {
		super();
		this.setDatum(datum);
		this.setAltBesitzer(altBesitzer);
		this.setNeuBesitzer(neuBesitzer);
		this.setBuch(buch);
	}


	
	// SETTER & GETTER:

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public User getAltBesitzer() {
		return altBesitzer;
	}

	public void setAltBesitzer(User altBesitzer) {
		this.altBesitzer = altBesitzer;
	}

	public User getNeuBesitzer() {
		return neuBesitzer;
	}

	public void setNeuBesitzer(User neuBesitzer) {
		this.neuBesitzer = neuBesitzer;
	}

	public Buch getBuch() {
		return buch;
	}

	public void setBuch(Buch buch) {
		this.buch = buch;
	}

}
