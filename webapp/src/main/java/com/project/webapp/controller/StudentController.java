package com.project.webapp.controller;

import com.project.webapp.model.Projekt;
import com.project.webapp.model.Student;
import com.project.webapp.model.Wiadomosc;
import com.project.webapp.model.Zadanie;
import com.project.webapp.service.StudentService;
import com.project.webapp.service.WiadomoscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class StudentController {

    private StudentService studentService;
    private WiadomoscService wiadomoscService;


    @Autowired
    public StudentController(StudentService studentService, WiadomoscService wiadomoscService) {
        this.studentService = studentService;
        this.wiadomoscService = wiadomoscService;
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


    @GetMapping("/studentEdit")
    public String studentEdit(@RequestParam(required = false) Integer studentId, Model model) {
        if(studentId != null) {
            model.addAttribute("student", studentService.getStudent(studentId).get());
        }else {
            Student student = new Student();
            model.addAttribute("student", student);
        }
        return "studentEdit";
    }

    @PostMapping("/studentEdit")
    public String studentEditSave(@ModelAttribute @Valid Student student, BindingResult bindingResult, Model model) {
        System.out.println(student.getHaslo());
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentService.getStudent(student.getStudentId()).get());
            return "studentEdit";
        }
        try {
            studentService.setStudent(student);
        } catch (HttpStatusCodeException e) {
            bindingResult.rejectValue(null, String.valueOf(e.getStatusCode().value()),
                    e.getStatusCode().getReasonPhrase());
            model.addAttribute("student", studentService.getStudent(student.getStudentId()).get());
            return "studentEdit";
        }

        return "redirect:/studentList";
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
    @PostMapping("/studentList/delete")
    public String deleteProjekt(Model model, @RequestParam Integer studentId, Pageable pageable) {
        wiadomoscService.deleteWiadomosc(studentId);
        studentService.deleteStudent(studentId);
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

    @GetMapping("/studentCzatList")
    public String studentCzatList(Model model, Pageable pageable) {
        model.addAttribute("studenci", studentService.getStudenci(pageable).getContent());
        model.addAttribute("size",10);
        model.addAttribute("page",0);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "studentCzatList";
    }

    @GetMapping("/studentCzatList/search")
    public String searchStudentCzatList(Model model, @RequestParam Integer size, @RequestParam String nazwa) {
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
            return "studentCzatList";
        }


        model.addAttribute("studenci", studentService.searchByNrIndeksuStartingWith(nazwa,pageable).getContent());
        model.addAttribute("size",size);
        model.addAttribute("page",0);
        model.addAttribute("nazwa",nazwa);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "studentCzatList";
    }

    @GetMapping("/studentCzatList/changePage")
    public String changePageStudentCzatList(Model model, @RequestParam Integer size, @RequestParam String nazwa,
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
            return "studentCzatList";
        }


        model.addAttribute("studenci", studentService.searchByNrIndeksuStartingWith(nazwa,pageable).getContent());
        model.addAttribute("size",size);
        model.addAttribute("page",page);
        model.addAttribute("nazwa",nazwa);
        model.addAttribute("nextPage",page+1);
        if(page==0)model.addAttribute("previousPage",0);
        else model.addAttribute("previousPage",page-1);
        return "studentCzatList";
    }


    @GetMapping("/czat")
    public String studentCzat(Model model, HttpSession session, @RequestParam Integer odbiorcaId){

        model.addAttribute("wiadomosci", wiadomoscService.getWiadomosci(odbiorcaId,
                Integer.parseInt(session.getAttribute("studentId").toString())
                ));
        model.addAttribute("odbiorcaId",odbiorcaId);

        return "czat";
    }
    @PostMapping("/czat")
    public void studentCzatSave(Model model, HttpSession session,
                                @RequestParam Integer odbiorcaId, @ModelAttribute @Valid Wiadomosc wiadomosc){
        wiadomoscService.saveWiadomosc(wiadomosc,
                Integer.parseInt(session.getAttribute("studentId").toString()),
                odbiorcaId);
        studentCzat(model, session,odbiorcaId);
    }





}
