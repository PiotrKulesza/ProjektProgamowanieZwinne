package com.project.webapp.controller;

import com.project.webapp.model.Student;
import com.project.webapp.model.Zadanie;
import com.project.webapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/studentDodaj")
    public String studentDodaj() {
        return "studentDodaj";
    }

    @PostMapping("/studentDodaj")
    public String studentDodaj(Model model, @ModelAttribute @Valid Student student) {
        student=studentService.setStudent(student);
        model.addAttribute("student",student);
        return "studentDodaj";
    }

    @GetMapping("/studentList")
    public String studentList(Model model, Pageable pageable) {
        model.addAttribute("studenci", studentService.getStudenci(pageable).getContent());
        model.addAttribute("size",10);
        model.addAttribute("page",0);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "studentList";
    }

    @GetMapping("/studentList/search")
    public String searchStudentList(Model model, @RequestParam Integer size, @RequestParam String nazwa) {
        Pageable pageable = PageRequest.of(0,size);

        try {
            double d = Double.parseDouble(nazwa);
        } catch (NumberFormatException nfe) {
            model.addAttribute("studenci", studentService.searchByNazwiskoStartsWithIgnoreCase(nazwa,pageable).getContent());
            model.addAttribute("size",size);
            model.addAttribute("page",0);
            model.addAttribute("nazwa",nazwa);
            model.addAttribute("nextPage",1);
            model.addAttribute("previousPage",0);
            return "studentList";
        }


        model.addAttribute("studenci", studentService.searchByNrIndeksuStartingWith(nazwa,pageable).getContent());
        model.addAttribute("size",size);
        model.addAttribute("page",0);
        model.addAttribute("nazwa",nazwa);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "studentList";
    }

    @GetMapping("/studentList/changePage")
    public String changePageStudentList(Model model, @RequestParam Integer size, @RequestParam String nazwa,
                                        @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page,size);

        try {
            double d = Double.parseDouble(nazwa);
        } catch (NumberFormatException nfe) {
            model.addAttribute("studenci", studentService.searchByNazwiskoStartsWithIgnoreCase(nazwa,pageable).getContent());
            model.addAttribute("size",size);
            model.addAttribute("page",page);
            model.addAttribute("nazwa",nazwa);
            model.addAttribute("nextPage",page+1);
            if(page==0)model.addAttribute("previousPage",0);
            else model.addAttribute("previousPage",page-1);
            return "studentList";
        }


        model.addAttribute("studenci", studentService.searchByNrIndeksuStartingWith(nazwa,pageable).getContent());
        model.addAttribute("size",size);
        model.addAttribute("page",page);
        model.addAttribute("nazwa",nazwa);
        model.addAttribute("nextPage",page+1);
        if(page==0)model.addAttribute("previousPage",0);
        else model.addAttribute("previousPage",page-1);
        return "studentList";
    }

}
