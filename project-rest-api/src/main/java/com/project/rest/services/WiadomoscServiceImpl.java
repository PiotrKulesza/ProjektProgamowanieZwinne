package com.project.rest.services;

import com.project.rest.model.Wiadomosc;
import com.project.rest.repositories.WiadomoscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WiadomoscServiceImpl implements WiadomoscService{

    private WiadomoscRepository wiadomoscRepository;

    @Autowired
    public WiadomoscServiceImpl(WiadomoscRepository wiadomoscRepository) {
        this.wiadomoscRepository = wiadomoscRepository;
    }

    @Override
    public List<Wiadomosc> getWiadomosci() {
        return wiadomoscRepository.findAll();
    }

    @Override
    public Wiadomosc saveWiadomosc(Wiadomosc wiadomosc) {
        return wiadomoscRepository.save(wiadomosc);
    }

}
