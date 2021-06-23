package com.project.webapp.controller;

import com.project.webapp.model.Student;
import com.project.webapp.model.Wykladowca;
import com.project.webapp.service.StudentService;
import com.project.webapp.service.WiadomoscService;
import com.project.webapp.service.WykladowcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    private StudentService studentService;
    private WykladowcaService wykladowcaService;

    @Autowired
    public LoginController(StudentService studentService, WykladowcaService wykladowcaService) {
        this.studentService = studentService;
        this.wykladowcaService = wykladowcaService;
    }

    @GetMapping("/loginStudent")
    public String loginPageStudent() {
        return "loginStudent";
    }

    @PostMapping ("/loginStudent")
    public String loginPageStudent(HttpSession session, @RequestParam String email, @RequestParam String haslo) {

        Optional<Student> optionalStudent = studentService.getByLogin(email,haslo);
        if(optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            session.setAttribute("role", "Student");
            session.setAttribute("studentId", student.getStudentId());
            return "studentHomePage";
        }else
            session.invalidate();
            return "homePage";

    }

    @GetMapping("/loginWykladowca")
    public String loginPageWykladowca() {
        return "loginWykladowca";
    }

    @PostMapping ("/loginWykladowca")
    public String loginPageWykladowca(HttpSession session, @RequestParam String email, @RequestParam String haslo) {

        Optional<Wykladowca> optionalWykladowca = wykladowcaService.getByLogin(email,haslo);
        if(optionalWykladowca.isPresent()) {
            Wykladowca wykladowca = optionalWykladowca.get();
            session.setAttribute("role", "Wykladowca");
            session.setAttribute("wykladowcaId", wykladowca.getWykladowcaId());
            return "wykladowcaHomePage";
        }else
            session.invalidate();
        return "homePage";

    }



    @GetMapping("/wykladowcaHomePage")
    public String indexWykladowca() {
        return "wykladowcaHomePage";
    }

    @GetMapping("/loggout")
    public String loggout(HttpSession session){
        session.invalidate();
        return "homePage";
    }

    @GetMapping("/")
    public String index() {
        return "homePage";
    }

    @GetMapping("/studentHomePage")
    public String indexStudent() {
        return "studentHomePage";
    }
}
