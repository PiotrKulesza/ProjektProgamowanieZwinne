package com.project.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wykladowca {


    private Integer wykladowcaId;


    private String imie;


    private String nazwisko;


    private String email;


    private String haslo;

}
