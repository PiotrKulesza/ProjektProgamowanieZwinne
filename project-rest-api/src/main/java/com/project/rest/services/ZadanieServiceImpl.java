package com.project.rest.services;

import com.project.rest.model.Projekt;
import com.project.rest.model.Zadanie;
import com.project.rest.repositories.ProjektRepository;
import com.project.rest.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ZadanieServiceImpl implements ZadanieService{

    private ZadanieRepository zadanieRepository;
    private ProjektService projektService;

    @Autowired
    public ZadanieServiceImpl(ZadanieRepository zadanieRepository,
                              ProjektService projektService) {
        this.zadanieRepository = zadanieRepository;
        this.projektService = projektService;
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
        Integer projektId = zadanieRepository.findById(zadanieId).get().getProjekt().getProjektId();
        zadanieRepository.delete(zadanieRepository.findById(zadanieId).get());

        List<Zadanie> zadaniaProjektu = projektService.getProjekt(projektId).get().getZadania();
        AtomicInteger kol = new AtomicInteger();
        kol.set(1);
        zadaniaProjektu.stream().sorted(Comparator.comparingInt(Zadanie::getKolejnosc)).forEach(s->{
            s.setKolejnosc(kol.get());
            kol.getAndIncrement();
            zadanieRepository.save(s);
        });

    }


}
