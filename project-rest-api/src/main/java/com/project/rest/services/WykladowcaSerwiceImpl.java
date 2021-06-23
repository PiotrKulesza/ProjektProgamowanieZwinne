package com.project.rest.services;

import com.project.rest.model.Wykladowca;
import com.project.rest.repositories.WykladowcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WykladowcaSerwiceImpl implements WykladowcaService{

    private WykladowcaRepository wykladowcaRepository;

    @Autowired
    public WykladowcaSerwiceImpl(WykladowcaRepository wykladowcaRepository) {
        this.wykladowcaRepository = wykladowcaRepository;
    }

    @Override
    public Optional<Wykladowca> getByLogin(String email, String haslo) {
        return wykladowcaRepository.findByEmailAndAndHaslo(email,haslo);
    }

}
