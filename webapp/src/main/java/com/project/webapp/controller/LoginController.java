package com.project.webapp.controller;

import com.project.webapp.model.Student;
import com.project.webapp.service.StudentService;
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

    @Autowired
    public LoginController(StudentService studentService) {
        this.studentService = studentService;
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

    @GetMapping("/test")
    public String test(HttpSession session) {
        //session.setAttribute("test","test");
        return "test";
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
