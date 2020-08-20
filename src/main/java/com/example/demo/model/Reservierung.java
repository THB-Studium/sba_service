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
public class Reservierung implements Serializable {

    // ATTRIBUTES:
    private static final long serialVersionUID = 2807389945509532998L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(nullable = false)
    private Date von;

    @Column(nullable = false)
    private Date bis;

    @ManyToOne
    @JoinColumn(name = "reservetraeger_id", nullable = false)
    private User reservetraeger;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "buch_id", nullable = false)
    private Buch buch;

    // KONSTRUKTOREN:

    public Reservierung(Date von, Date bis, User reserveträger) {
        super();
        this.setVon(von);
        this.setBis(bis);
        this.setReservetraeger(reserveträger);
    }

    // SETTER & GETTER:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getVon() {
        return von;
    }

    public void setVon(Date von) {
        this.von = von;
    }

    public Date getBis() {
        return bis;
    }

    public void setBis(Date bis) {
        this.bis = bis;
    }

    public User getReservetraeger() {
        return reservetraeger;
    }

    public void setReservetraeger(User reservetraeger) {
        this.reservetraeger = reservetraeger;
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

}
