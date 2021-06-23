package com.project.webapp.service;

import com.project.webapp.model.Wiadomosc;
import com.project.webapp.model.Wykladowca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WiadomoscService {
    List<Wiadomosc> getWiadomosci(Integer nadStudentId, Integer adStudentId);

    Wiadomosc saveWiadomosc(Wiadomosc wiadomosc,Integer nadStudentId, Integer adStudentId);


}
