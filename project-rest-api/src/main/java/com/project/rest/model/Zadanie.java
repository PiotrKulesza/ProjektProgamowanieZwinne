package com.project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="zadanie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zadanie {

	@Id
	@GeneratedValue
	@Column(name="zadanie_id")
	private Integer zadanieId;

	@ManyToOne
	@JoinColumn(name = "projekt_id")
	private Projekt projekt;

	@Column(nullable = false, length = 50)
	private String nazwa;

	@Column(length = 1000)
	private String opis;

	@Column(nullable = false, name = "dataczas_dodania")
	private LocalDateTime dataczasDodania;

	@Column(name = "kolejnosc")
	private Integer kolejnosc;



}
