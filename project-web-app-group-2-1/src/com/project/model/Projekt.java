package com.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;



@Entity
@Table(name = "projekt")
public class Projekt {
	
	@Id
	@GeneratedValue
	@Column(name = "projekt_id")
	private Integer projektId;
	
	@Column(nullable = false, length = 50)
	private String nazwa;
	
	@Column(nullable = false, length = 1000)
	private String opis;
	
	@Column(nullable = false, name = "dataczas_utworzenia")
	private LocalDateTime dataczasUtworzenia;
	
	@Column(nullable = false, name = "data_oddania")
	private LocalDate dataOddania;
	
	@OneToMany(mappedBy = "projekt")
	private List<Zadanie> zadania;
	
	@ManyToMany
	@JoinTable(name = "projekt_student",
	joinColumns = {@JoinColumn(name="projekt_id")},
	inverseJoinColumns = {@JoinColumn(name="student_id")})
	private Set<Student> studenci;

	public Projekt() {
		super();
	}

	public Projekt(Integer projektId, String nazwa, String opis, LocalDateTime dataczasUtworzenia, LocalDate dataOddania) {
		super();
		this.projektId = projektId;
		this.nazwa = nazwa;
		this.opis = opis;
		this.dataczasUtworzenia = dataczasUtworzenia;
		this.dataOddania = dataOddania;
	}

	public Integer getProjektId() {
		return projektId;
	}

	public void setProjektId(Integer projektId) {
		this.projektId = projektId;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDateTime getDataczasUtworzenia() {
		return dataczasUtworzenia;
	}

	public void setDataczasUtworzenia(LocalDateTime dataczasUtworzenia) {
		this.dataczasUtworzenia = dataczasUtworzenia;
	}

	public LocalDate getDataOddania() {
		return dataOddania;
	}

	public void setDataOddania(LocalDate dataOddania) {
		this.dataOddania = dataOddania;
	}

	public List<Zadanie> getZadania() {
		return zadania;
	}

	public void setZadania(List<Zadanie> zadania) {
		this.zadania = zadania;
	}

	@Override
	public String toString() {
		return "Projekt [nazwa=" + nazwa + ", opis=" + opis + ", dataczasUtworzenia=" + dataczasUtworzenia
				+ ", dataOddania=" + dataOddania + "]";
	}
	
	
	
	
}
