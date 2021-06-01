package com.project.webapp.controller;

import com.project.webapp.model.Projekt;
import com.project.webapp.model.Zadanie;
import com.project.webapp.service.ZadanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ZadaniaContoller {

    private ZadanieService zadanieService;

    @Autowired
    public ZadaniaContoller(ZadanieService zadanieService){
        this.zadanieService = zadanieService;
    }

    @GetMapping("/zadaniaList")
    public String zadaniaList(Model model, Pageable pageable, Integer projektId) {

        model.addAttribute("zadania", zadanieService.getZadanieProjektu(projektId,pageable).getContent());
        model.addAttribute("projektId",projektId);
        model.addAttribute("size",10);
        model.addAttribute("page",0);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "zadaniaList";
    }

    @GetMapping("/zadanieDodaj")
    public String zadanieDodaj(Model model, Integer projektId){
        model.addAttribute("projektId",projektId);
        return "zadanieDodaj";
    }

    @PostMapping("/zadanieDodaj")
    public String zadanieDodaj(Model model, Pageable pageable , Integer projektId, @ModelAttribute @Valid Zadanie zadanie){
        zadanieService.setZadanie(zadanie,projektId);
        model.addAttribute("zadania", zadanieService.getZadanieProjektu(projektId,pageable).getContent());
        model.addAttribute("projektId",projektId);
        model.addAttribute("size",10);
        model.addAttribute("page",0);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "zadaniaList";
    }

}
