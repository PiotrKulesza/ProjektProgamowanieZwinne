package com.project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wiadomosc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wiadmosc {

    @Id
    @GeneratedValue
    @Column(name="wiadomosc_id")
    private Long wiadomoscId;

    @Column(nullable = false, length = 1000)
    private String tekst;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student nadawca;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student adresat;

}
