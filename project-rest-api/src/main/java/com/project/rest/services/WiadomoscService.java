package com.project.rest.services;

import com.project.rest.model.Wiadomosc;

import java.util.List;

public interface WiadomoscService {
    List<Wiadomosc> getWiadomosci();

    Wiadomosc saveWiadomosc(Wiadomosc wiadomosc);
}
