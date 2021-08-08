package com.project.webapp.service;

import com.project.webapp.model.Zadanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ZadanieService {
    Optional<Zadanie> getZadanie(Integer zadanieId);

    Page<Zadanie> getZadanieProjektu (Integer projektId, Pageable pageable);
    Zadanie setZadanie(Zadanie zadanie, Integer projektId);
    void deleteZadanie(Integer zadanieId, Integer projektId);
}
