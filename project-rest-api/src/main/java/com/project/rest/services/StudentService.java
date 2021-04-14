package com.project.rest.services;

import com.project.rest.model.Projekt;
import com.project.rest.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentService {
    Optional<Projekt> getStudent(Integer projektId);
    Projekt setStudent(Projekt projekt);
    void deleteStudent(Integer projektId);
    Page<Student> getStudenci(Pageable pageable);
    Student searchByNrIndeksu(String nrIndeksu);

}
