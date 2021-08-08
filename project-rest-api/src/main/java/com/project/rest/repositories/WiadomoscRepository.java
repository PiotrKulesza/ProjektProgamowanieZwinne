package com.project.rest.repositories;

import com.project.rest.model.Student;
import com.project.rest.model.Wiadomosc;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface WiadomoscRepository extends JpaRepository<Wiadomosc,Integer> {

    void deleteByNadawcaOrAdresat(Student nadawca, Student adresat);
}
