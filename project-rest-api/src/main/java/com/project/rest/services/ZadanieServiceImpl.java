package com.project.rest.services;

import com.project.rest.repositories.StudentRepository;
import com.project.rest.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ZadanieServiceImpl implements ZadanieService{

    private ZadanieRepository zadanieRepository;

    @Autowired
    public ZadanieServiceImpl(ZadanieRepository zadanieRepository) {
        this.zadanieRepository = zadanieRepository;
    }

}
