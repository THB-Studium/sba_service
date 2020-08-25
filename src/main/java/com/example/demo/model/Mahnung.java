package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mahnung implements Serializable {

    // ATTRIBUTES:
    private static final long serialVersionUID = -6163449312340776687L;
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private Date createdDate;
    private double mahnungBetrag;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "buch_id")
    private Buch buch;
    
    
    // KONSTRUKTOREN 
    
    public Mahnung(Date createdDate) {
        super();
        this.setCreatedDate(createdDate);
    }

    public Mahnung() {
        this(null);
    }

    // SETTER && GETTER 
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Buch getBuch() {
        return buch;
    }

    public void setBuch(Buch buch) {
        this.buch = buch;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public double getMahnungBetrag() {
        return mahnungBetrag;
    }

    public void setMahnungBetrag(double mahnungBetrag) {
        this.mahnungBetrag = mahnungBetrag;
    }

}
