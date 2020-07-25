package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

@Entity
public class Reservierung implements Serializable {

	// ATTRIBUTES:
	private static final long serialVersionUID = 2807389945509532998L;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	@NotNull
	private Date von;
	@NotNull
	private Date bis;
	

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "userId", nullable = false)
	private User reserveträger;
	
	@NotNull
	@OneToMany(mappedBy = "aktuelleReservierung", fetch = FetchType.EAGER)
	private Set<Buch> buecher;

	
	
	// KONSTRUKTOREN:
	
	public Reservierung(Date von, Date bis, User reserveträger, Set<Buch> buecher) {
		super();
		this.setVon(von);
		this.setBis(bis);
		this.setReserveträger(reserveträger);
		this.setBuecher(buecher);
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

	public User getReserveträger() {
		return reserveträger;
	}

	public void setReserveträger(User reserveträger) {
		this.reserveträger = reserveträger;
	}

	public Set<Buch> getBuecher() {
		return buecher;
	}

	public void setBuecher(Set<Buch> buecher) {
		this.buecher = buecher;
	}

}
