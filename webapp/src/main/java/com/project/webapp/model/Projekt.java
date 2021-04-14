package com.project.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Projekt {


	private Integer projektId;


	private String nazwa;


	private String opis;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime dataczasUtworzenia;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataOddania;


	@JsonIgnoreProperties({"projekt"})
	private List<Zadanie> zadania;


	private Set<Student> studenci;


}
