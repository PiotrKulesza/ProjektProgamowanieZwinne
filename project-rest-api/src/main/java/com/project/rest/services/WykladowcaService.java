package com.project.rest.services;

import com.project.rest.model.Wykladowca;

import java.util.Optional;

public interface WykladowcaService {
    Optional<Wykladowca> getByLogin(String email, String haslo) ;
}
