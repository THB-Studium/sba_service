package com.example.demo.search.criteria;

public class BuchCriteria {
    
    // ATTRIBUTES:
    private String titel;
    private String autor;
    private String erscheinungsdatum;
    private long erscheinungsjahr;
    private long isbn13;
    private long isbn10;
    private String medienart;
    private String verlag;
    private String ausgabe; // edition
    private String uebersicht; // overview
    private String kategorie; // subjects
    private String zustand;
    private String anmerkungen;
    private String sprache;
    private String signatur;
    
    // KONSTRUKTOREN:

    public BuchCriteria() {
    }

    
    // SETTER & GETTER:

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

    public String getSignatur() {
        return signatur;
    }

    public void setSignatur(String signatur) {
        this.signatur = signatur;
    } 
}
