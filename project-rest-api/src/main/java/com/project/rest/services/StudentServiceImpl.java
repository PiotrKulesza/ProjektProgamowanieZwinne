package com.project.rest.services;

import com.project.rest.model.Projekt;
import com.project.rest.model.Student;
import com.project.rest.repositories.ProjektRepository;
import com.project.rest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    private ProjektRepository projektRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ProjektRepository projektRepository) {
        this.studentRepository = studentRepository;
        this.projektRepository = projektRepository;
    }


    @Override
    public Optional<Student> getStudent(Integer studentId) {

        return studentRepository.findById(studentId);
    }

    @Override
    public Student setStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Integer studentId) {

        Student student = getStudent(studentId).get();
        for(Projekt projekt : student.getProjekty()){
            Set<Student> students = projekt.getStudenci();
            students.remove(projekt);
            projekt.setStudenci(students);
            projektRepository.save(projekt);
        }
        studentRepository.delete(student);

    }

    @Override
    public Page<Student> getStudenci(Pageable pageable) {
        //System.out.println(studentRepository.findAll(pageable).getContent().get(0));

        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> getStudenciWitchoutOne(Integer studentId, Pageable pageable) {
        //System.out.println(studentRepository.findAll(pageable).getContent().get(0));

        return studentRepository.findAllByStudentIdNot(studentId,pageable);
    }

    @Override
    public Optional<Student> searchByNrIndeksu(String nrIndeksu) {
        return studentRepository.findByNrIndeksu(nrIndeksu);
    }

    @Override
    public Page<Student> searchByNrIndeksuStartingWith(String nrIndeksu, Pageable pageable) {
        return studentRepository.findByNrIndeksuStartingWith(nrIndeksu,pageable);
    }

    @Override
    public Page<Student> searchByNazwiskoStartsWithIgnoreCase(String nazwisko, Pageable pageable) {
        return studentRepository.findByNazwiskoStartsWithIgnoreCase(nazwisko,pageable);
    }

    @Override
    public Optional<Student> getByLogin(String email, String haslo) {
        return studentRepository.findByEmailAndAndHaslo(email,haslo);
    }


}
