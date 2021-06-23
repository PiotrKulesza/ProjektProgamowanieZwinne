package com.project.rest.repositories;

import com.project.rest.model.Wykladowca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WykladowcaRepository extends JpaRepository<Wykladowca,Integer> {
    Optional<Wykladowca> findByEmailAndAndHaslo(String email, String haslo);
}
