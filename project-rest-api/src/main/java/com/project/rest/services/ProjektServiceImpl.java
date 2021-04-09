package com.project.rest.services;

import com.project.rest.model.Projekt;
import com.project.rest.model.Zadanie;
import com.project.rest.repositories.ProjektRepository;
import com.project.rest.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProjektServiceImpl implements ProjektService{

    private ProjektRepository projektRepository;
    private ZadanieRepository zadanieRepository;

    @Autowired
    public ProjektServiceImpl(ProjektRepository projektRepository, ZadanieRepository zadanieRepository){
        this.projektRepository = projektRepository;
        this.zadanieRepository = zadanieRepository;
    }


    @Override
    public Optional<Projekt> getProjekt(Integer projektId) {
        return projektRepository.findById(projektId);
    }

    @Override
    public Projekt setProjekt(Projekt projekt) {
        return projektRepository.save(projekt);
    }

    @Override
    @Transactional
    public void deleteProjekt(Integer projektId) {
        for (Zadanie zadanie : zadanieRepository.findZadaniaProjektu(projektId)) {
            zadanieRepository.delete(zadanie);
        }
        projektRepository.deleteById(projektId);
    }

    @Override
    public Page<Projekt> getProjekty(Pageable pageable) {

        Page<Projekt> projekty = projektRepository.findAll(pageable);

        return projekty;
    }

    @Override
    public Page<Projekt> searchByNazwa(String nazwa, Pageable pageable) {
        Page<Projekt> projekty =projektRepository.findByNazwaContainingIgnoreCase(nazwa,pageable);

        return projekty;
    }
}
