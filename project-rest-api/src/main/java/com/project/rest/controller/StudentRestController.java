package com.project.rest.controller;

import com.project.rest.model.Student;
import com.project.rest.services.StudentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/studenci")
    ResponseEntity<Void> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.setStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{studentId}").buildAndExpand(createdStudent.getStudentId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/studenci/{studentId}")
    public ResponseEntity<Void> updateStudent(@Valid @RequestBody Student student,
                                              @PathVariable Integer studentId) {
        return studentService.getStudent(studentId)
                .map(p -> {
                    studentService.setStudent(student);
                    return new ResponseEntity<Void>(HttpStatus.OK); // 200 (można też zwracać 204 - No content)
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
    }

    @DeleteMapping("/studenci/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer studentId) {
        return studentService.getStudent(studentId).map(p -> {
            studentService.deleteStudent(studentId);
            return new ResponseEntity<Void>(HttpStatus.OK); // 200
        }).orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
    }

    @GetMapping(value = "/studenci")
    Page<Student> getStudenci(Pageable pageable) {
        return studentService.getStudenci(pageable);
    }

    @GetMapping(value = "/studenci", params = "nrIndeksu")
    Optional<Student> getStudneciByNrIndeksu(@RequestParam String nrIndeksu){
        return studentService.searchByNrIndeksu(nrIndeksu);

    }

    @GetMapping(value = "/studenci/nrIndeksuStartedWith", params = "nrIndeksu")
    Page<Student> getStudneciByNrIndeksuStartingWith(@RequestParam String nrIndeksu, Pageable pageable){
        return studentService.searchByNrIndeksuStartingWith(nrIndeksu,pageable);

    }
    @GetMapping(value = "/studenci", params = "nazwisko")
    Page<Student> getStudneciByNazwiskoStartsWithIgnoreCase(@RequestParam String nazwisko, Pageable pageable){
        return studentService.searchByNazwiskoStartsWithIgnoreCase(nazwisko,pageable);

    }

    @GetMapping(value = "/studenci/login", params = {"email","haslo"})
    Optional<Student> getByLogin(@RequestParam String email, @RequestParam String haslo){
        return studentService.getByLogin(email,haslo);

    }



}
