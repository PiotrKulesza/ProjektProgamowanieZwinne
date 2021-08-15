package com.project.rest.services;

import com.project.rest.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentService {
    Optional<Student> getStudent(Integer studentId);
    Student setStudent(Student student);
    void deleteStudent(Integer studentId);
    Page<Student> getStudenci(Pageable pageable);
    Optional<Student> searchByNrIndeksu(String nrIndeksu);
    Page<Student> searchByNrIndeksuStartingWith(String nrIndeksu,Pageable pageable);
    Page<Student> searchByNazwiskoStartsWithIgnoreCase(String nazwisko,Pageable pageable);
    Optional<Student> getByLogin(String email, String haslo) ;
    Page<Student> getStudenciWitchoutOne(Integer studentId, Pageable pageable);

}
