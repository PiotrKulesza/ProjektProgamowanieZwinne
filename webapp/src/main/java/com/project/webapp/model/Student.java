package com.project.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

	private Integer studentId;


	private String imie;


	private String nazwisko;


	private String nrIndeksu;


	private String email;

	private boolean stacjonarny;


	private Set<Projekt> projekty;


}
