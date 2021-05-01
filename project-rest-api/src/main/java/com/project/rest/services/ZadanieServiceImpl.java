package com.project.rest.services;

import com.project.rest.model.Zadanie;
import com.project.rest.repositories.ProjektRepository;
import com.project.rest.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ZadanieServiceImpl implements ZadanieService{

    private ZadanieRepository zadanieRepository;

    @Autowired
    public ZadanieServiceImpl(ZadanieRepository zadanieRepository) {
        this.zadanieRepository = zadanieRepository;
    }

    @Override
    public Optional<Zadanie> getZadanie(Integer zadanieId) {
        return zadanieRepository.findById(zadanieId);
    }



    @Override
    public Page<Zadanie> getZadanieProjektu(Integer projektId, Pageable pageable) {
        return zadanieRepository.findZadaniaProjektu(projektId,pageable);
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        return zadanieRepository.save(zadanie);
    }

    @Override
    public void deleteZadanie(Integer zadanieId) {
        List<Zadanie> zadaniaProjektu = zadanieRepository.findZadaniaProjektu(zadanieRepository.findById(zadanieId).get().getProjekt().getProjektId());
        zadanieRepository.delete(zadanieRepository.findById(zadanieId).get());
        //<Zadanie> zadaniaProjektu = zadanieRepository.findZadaniaProjektu(projektId);
        AtomicInteger kol = new AtomicInteger();
        zadaniaProjektu.stream().sorted(Comparator.comparingInt(Zadanie::getKolejnosc)).forEach(s->{
            s.setKolejnosc(kol.get());
            kol.getAndIncrement();
            zadanieRepository.save(s);
        });

    }


}
