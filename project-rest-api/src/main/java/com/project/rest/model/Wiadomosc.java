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
public class Wiadomosc {

    @Id
    @GeneratedValue
    @Column(name="wiadomosc_id")
    private Long wiadomoscId;

    @Column(nullable = false, length = 1000)
    private String tekst;


    @ManyToOne
    @JoinColumn(name = "student_id_nad")
    private Student nadawca;


    @ManyToOne
    @JoinColumn(name = "student_id_ad")
    private Student adresat;

}
