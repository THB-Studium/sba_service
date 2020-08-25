package com.example.demo.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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
    private String erscheinungsdatum;
    private long erscheinungsjahr;
    private String isbn13;
    private String isbn10;
    private String isbn;
    private String preis;
    private String medienart;
    private String verlag;
    private String ausgabe; // edition
    private long seiten;
    @Lob
    private String uebersicht; // overview
    private String kategorie; // subjects
    private String zustand;
    @Lob
    private String anmerkungen;
    private String sprache;
    private long exemplare;
    private String signatur;
    private String verfuegbarkeit;

    // KONSTRUKTOREN:

    public Buch(String titel, String autor, String erscheinungsdatum, String isbn13, String isbn10, String isbn, String preis, String medienart,
            String verlag, long erscheinungsjahr, String ausgabe, long seiten, String uebersicht, String kategorie,
            String zustand, String anmerkungen, String sprache, long exemplare, String signatur, String verfuegbarkeit) {
        super();
        this.titel = titel;
        this.autor = autor;
        this.erscheinungsdatum = erscheinungsdatum;
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
        this.isbn = isbn;
        this.preis = preis;
        this.medienart = medienart;
        this.verlag = verlag;
        this.erscheinungsjahr = erscheinungsjahr;
        this.ausgabe = ausgabe;
        this.seiten = seiten;
        this.uebersicht = uebersicht;
        this.kategorie = kategorie;
        this.zustand = zustand;
        this.anmerkungen = anmerkungen;
        this.sprache = sprache;
        this.exemplare = exemplare;
        this.signatur = signatur;
        this.verfuegbarkeit = verfuegbarkeit;
    }

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

    public String getErscheinungsdatum() {
        return erscheinungsdatum;
    }

    public void setErscheinungsdatum(String erscheinungsdatum) {
        this.erscheinungsdatum = erscheinungsdatum;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        this.setIsbn(isbn13);
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
