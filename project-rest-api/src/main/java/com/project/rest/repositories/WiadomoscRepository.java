package com.project.rest.repositories;

import com.project.rest.model.Wiadomosc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WiadomoscRepository extends JpaRepository<Wiadomosc,Integer> {
}
