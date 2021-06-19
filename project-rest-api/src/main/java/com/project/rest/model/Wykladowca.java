package com.project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wykladowca")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wykladowca {

    @Id
    @GeneratedValue
    @Column(name="wykladowca_id")
    private Integer wykladowcaId;

    @Column(nullable = false, length = 50)
    private String imie;

    @Column(nullable = false, length = 100)
    private String nazwisko;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String haslo;

}
