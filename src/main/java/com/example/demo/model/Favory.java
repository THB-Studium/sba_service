package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Favory implements Serializable {

    // ATTRIBUTES:
    private static final long serialVersionUID = -6737559564933726639L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "favory_buch",
            joinColumns = @JoinColumn(name = "favory_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "buch_id", nullable = false, updatable = false))
    private Set<Buch> buecher = new HashSet<>();

    // KONSTRUKTOREN:

    public Favory(User user, Set<Buch> favories) {
        this.setUser(user);
        this.setBuecher(buecher);
    }

    public Favory() {
        this(null, null);
    }

    // SETTER & GETTER:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Buch> getBuecher() {
        return buecher;
    }

    public void setBuecher(Set<Buch> buecher) {
        this.buecher = buecher;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
