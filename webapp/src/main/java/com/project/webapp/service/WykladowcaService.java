package com.project.webapp.service;

import com.project.webapp.model.Wykladowca;

import java.util.Optional;

public interface WykladowcaService {
    Optional<Wykladowca> getByLogin(String email, String haslo) ;
}
