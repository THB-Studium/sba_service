package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Weiterleitung implements Serializable {

    // ATTRIBUTES:
    private static final long serialVersionUID = -8516974422426875288L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(nullable = false)
    private Date datum;

    @ManyToOne
    @JoinColumn(name = "alt_besitzer_id")
    private User altBesitzer;

    @ManyToOne
    @JoinColumn(name = "neu_besitzer_id")
    private User neuBesitzer;

    @ManyToOne
    @JoinColumn(name = "buch_id")
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

    public Weiterleitung() {

    }

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

    // @Override
    // public String getKey() {
    // return id.toString();
    // }
    //
    // @Override
    // public String getUserPrincipal() {
    // return neuBesitzer.getBenutzernummer();
    // }

}
