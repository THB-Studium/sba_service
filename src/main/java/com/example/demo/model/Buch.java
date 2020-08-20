package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Buch implements Serializable {

    // ATTRIBUTES:
    private static final long serialVersionUID = 3054522163744559148L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String titel;
    private String autor;
    private Date datum;
    private long isbn13;
    private long isbn10;
    private long preis;
    private String medienart;
    private String verlag;
    private long erscheinungsjahr;
    private String ausgabe; // edition
    private long seiten;
    private String uebersicht; // overview
    private String kategorie; // subjects
    private String zustand;
    private String anmerkungen;
    private String sprache;
    private long exemplare;
    private String signatur;
    private String verfuegbarkeit;

    // KONSTRUKTOREN:

    public Buch() {
    }

    // SETTER & GETTER:

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public long getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(long isbn13) {
        this.isbn13 = isbn13;
    }

    public long getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(long isbn10) {
        this.isbn10 = isbn10;
    }

    public long getPreis() {
        return preis;
    }

    public void setPreis(long preis) {
        this.preis = preis;
    }

    public String getMedienart() {
        return medienart;
    }

    public void setMedienart(String medienart) {
        this.medienart = medienart;
    }

    public String getVerlag() {
        return verlag;
    }

    public void setVerlag(String verlag) {
        this.verlag = verlag;
    }

    public long getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(long erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public String getAusgabe() {
        return ausgabe;
    }

    public void setAusgabe(String ausgabe) {
        this.ausgabe = ausgabe;
    }

    public long getSeiten() {
        return seiten;
    }

    public void setSeiten(long seiten) {
        this.seiten = seiten;
    }

    public String getUebersicht() {
        return uebersicht;
    }

    public void setUebersicht(String uebersicht) {
        this.uebersicht = uebersicht;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getZustand() {
        return zustand;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    public String getAnmerkungen() {
        return anmerkungen;
    }

    public void setAnmerkungen(String anmerkungen) {
        this.anmerkungen = anmerkungen;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public long getExemplare() {
        return exemplare;
    }

    public void setExemplare(long exemplare) {
        this.exemplare = exemplare;
    }

    public String getVerfuegbarkeit() {
        return verfuegbarkeit;
    }

    public void setVerfuegbarkeit(String verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public String getSignatur() {
        return signatur;
    }

    public void setSignatur(String signatur) {
        this.signatur = signatur;
    }

}
