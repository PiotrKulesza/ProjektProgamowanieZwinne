package com.project.rest.controller;

import com.project.rest.model.Student;
import com.project.rest.model.Wiadomosc;
import com.project.rest.services.StudentService;
import javax.validation.Valid;

import com.project.rest.services.WiadomoscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private StudentService studentService;
    private WiadomoscService wiadomoscService;


    @Autowired
    public StudentRestController(StudentService studentService, WiadomoscService wiadomoscService) {
        this.studentService = studentService;
        this.wiadomoscService = wiadomoscService;
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

    @GetMapping(value = "/studenci/wiadomosci",params = {"nadStudentId","adStudentId"})
    Page<Wiadomosc> getWiadomoscPage(@RequestParam Integer nadStudentId, @RequestParam Integer adStudentId, Pageable pageable){

        List<Wiadomosc> wiadomoscList = wiadomoscService.getWiadomosci();
        List<Wiadomosc> wiadomoscListStudent = wiadomoscList.stream().filter(s->(s.getAdresat()
                .getStudentId().equals(adStudentId)
        && s.getNadawca().getStudentId().equals(nadStudentId)) || (s.getAdresat().getStudentId().equals(nadStudentId)
                && s.getNadawca().getStudentId().equals(adStudentId))).collect(Collectors.toList());
        int start = (pageable.getPageNumber() - 1) * pageable.getPageSize();
        int end = (start + pageable.getPageSize()) > wiadomoscListStudent.size() ? wiadomoscListStudent.size() :
                (pageable.getPageSize() * pageable.getPageNumber());

        return new PageImpl<>(wiadomoscList.subList(start, end),pageable,wiadomoscListStudent.size());
    }

    @PostMapping(path = "/studenci/wiadomosci")
    ResponseEntity<Void> createStudent(@Valid @RequestBody Wiadomosc wiadomosc, @RequestParam Integer nadStudentId,  @RequestParam Integer adStudentId) {
        Student nadawca = studentService.getStudent(nadStudentId).get();
        Student adresat = studentService.getStudent(adStudentId).get();
        wiadomosc.setAdresat(adresat);
        wiadomosc.setNadawca(nadawca);
        Wiadomosc wiadomoscReturn = wiadomoscService.saveWiadomosc(wiadomosc);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{studentId}").buildAndExpand(wiadomoscReturn.getWiadomoscId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
