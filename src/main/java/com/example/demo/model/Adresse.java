package com.example.demo.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Adresse implements Serializable {

    // ATTRIBUTES:
    private static final long serialVersionUID = 4393203046747137818L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String strasse;
    private long plz;
    private String stadt;
    private String land;

    // KONSTRUKTOREN:

    public Adresse(String strasse, long plz, String stadt, String land) {
        super();
        this.setStrasse(strasse);
        this.setPlz(plz);
        this.setLand(land);
        this.setStadt(stadt);
    }

    public Adresse() {
        this(null, 0, null, null);
    }

    // SETTER & GETTER:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public long getPlz() {
        return plz;
    }

    public void setPlz(long plz) {
        this.plz = plz;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

}
