package com.project.webapp.service;

import com.project.webapp.model.Wiadomosc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WiadomoscService {
    List<Wiadomosc> getWiadomosci(Integer nadStudentId, Integer adStudentId);

    Wiadomosc saveWiadomosc(Wiadomosc wiadomosc,Integer nadStudentId, Integer adStudentId);
}
