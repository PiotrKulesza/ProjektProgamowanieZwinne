package com.project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue
	@Column(name="student_id")
	private Integer studentId;

	@Column(nullable = false, length = 50)
	private String imie;

	@Column(nullable = false, length = 100)
	private String nazwisko;

	@Column(unique=true, nullable = false, length = 50, name = "nr_indeksu")
	private String nrIndeksu;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false)
	private boolean stacjonarny;

	@ManyToMany(mappedBy = "studenci")
	private Set<Projekt> projekty;


}
