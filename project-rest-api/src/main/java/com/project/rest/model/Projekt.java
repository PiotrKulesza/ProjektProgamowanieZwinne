package com.project.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@JsonIgnoreProperties({"projekt"})
	private List<Zadanie> zadania;

	@ManyToMany
	@JoinTable(name = "projekt_student",
	joinColumns = {@JoinColumn(name="projekt_id")},
	inverseJoinColumns = {@JoinColumn(name="student_id")})
	private Set<Student> studenci;


}
