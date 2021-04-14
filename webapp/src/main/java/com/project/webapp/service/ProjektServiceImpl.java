package com.project.webapp.service;

import com.project.webapp.model.Projekt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ProjektServiceImpl implements ProjektService {
    @Override
    public Optional<Projekt> getProjekt(Integer projektId) {
        return Optional.empty();
    }

    @Override
    public Projekt setProjekt(Projekt projekt) {
        return null;
    }

    @Override
    public void deleteProjekt(Integer projektId) {

    }

    @Override
    public Page<Projekt> getProjekty(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Projekt> searchByNazwa(String nazwa, Pageable pageable) {
        return null;
    }
}
