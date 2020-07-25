package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
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
	private String verlag;
	private long erscheinungsjahr;
	private Date datum;
	private String iSBN;
	private String medienart;
	private long seiten;
	private String zustand;
	private String anmerkungen;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "reservierungId", nullable = false)
	private Reservierung aktuelleReservierung;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "ausleihenId", nullable = false)
	private Ausleihen aktuelleAusleihen;
	
	
	
	// KONSTRUKTOREN:
	
	public Buch(String titel, String autor, String verlag, long erscheinungsjahr, Date datum, String iSBN,
			String medienart, long seiten, String zustand, String anmerkungen) {
		super();
		this.setTitel(titel);
		this.setAutor(autor);
		this.setVerlag(verlag);
		this.setErscheinungsjahr(erscheinungsjahr);
		this.setDatum(datum);
		this.setiSBN(iSBN);
		this.setMedienart(medienart);
		this.setSeiten(seiten);
		this.setZustand(zustand);
		this.setAnmerkungen(anmerkungen);
	}

	public Buch() {
		this(null, null, null, 0, null, null, null, 0, null, null);
	}

	public UUID getId() {
		return id;
	}

	
	
	// SETTER & GETTER:
	
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getiSBN() {
		return iSBN;
	}

	public void setiSBN(String iSBN) {
		this.iSBN = iSBN;
	}

	public String getMedienart() {
		return medienart;
	}

	public void setMedienart(String medienart) {
		this.medienart = medienart;
	}

	public long getSeiten() {
		return seiten;
	}

	public void setSeiten(long seiten) {
		this.seiten = seiten;
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

	public Reservierung getAktuelleReservierungen() {
		return aktuelleReservierung;
	}

	public void setAktuelleReservierungen(Reservierung aktuelleReservierung) {
		this.aktuelleReservierung = aktuelleReservierung;
	}

	public Ausleihen getAktuelleAusleihen() {
		return aktuelleAusleihen;
	}

	public void setAktuelleAusleihen(Ausleihen aktuelleAusleihen) {
		this.aktuelleAusleihen = aktuelleAusleihen;
	}

}
