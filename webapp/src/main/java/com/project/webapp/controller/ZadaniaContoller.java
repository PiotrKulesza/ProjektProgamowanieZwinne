package com.project.webapp.controller;

import com.project.webapp.model.Projekt;
import com.project.webapp.model.Student;
import com.project.webapp.model.Zadanie;
import com.project.webapp.service.ProjektService;
import com.project.webapp.service.ZadanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ZadaniaContoller {

    private ZadanieService zadanieService;
    private ProjektService projektService;

    @Autowired
    public ZadaniaContoller(ZadanieService zadanieService, ProjektService projektService){
        this.projektService = projektService;
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

    @PostMapping ("/zadanieUsun")
    public String zadanieUsun(Model model, Integer zadanieId, Integer projektId, Pageable pageable){

        zadanieService.deleteZadanie(zadanieId,projektId);
        model.addAttribute("zadania", zadanieService.getZadanieProjektu(projektId,pageable).getContent());
        model.addAttribute("projektId",projektId);
        model.addAttribute("size",10);
        model.addAttribute("page",0);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "zadaniaList";
    }

    @GetMapping("/zadaniaEdit")
    public String zadanieEdit(Model model, Integer projektId, Integer zadanieId){

        if(zadanieId != null) {
            model.addAttribute("projektId",projektId);
            model.addAttribute("zadanie",zadanieService.getZadanie(zadanieId).get());
        }else {
            model.addAttribute("projektId",projektId);
            return "zadaniaList";
        }
        return "zadaniaEdit";
    }

    @PostMapping("/zadaniaEdit")
    public String zadanieEdit(@ModelAttribute @Valid Zadanie zadanie, BindingResult bindingResult, Model model
            , Pageable pageable){
        Zadanie zadanieEdit = zadanieService.getZadanie(zadanie.getZadanieId()).get();

        if (bindingResult.hasErrors()) {
            model.addAttribute("zadanie", zadanieService.getZadanie(zadanie.getZadanieId()).get());
            model.addAttribute("projektId",zadanie.getProjekt().getProjektId());
            return "zadaniaEdit";
        }
        try {
            zadanieEdit.setOpis(zadanie.getOpis());
            zadanieEdit.setNazwa(zadanie.getNazwa());
            zadanieService.setZadanie(zadanieEdit,zadanieEdit.getProjekt().getProjektId());
        } catch (HttpStatusCodeException e) {
            bindingResult.rejectValue(null, String.valueOf(e.getStatusCode().value()),
                    e.getStatusCode().getReasonPhrase());
            model.addAttribute("zadania", zadanieService
                    .getZadanieProjektu(zadanieEdit.getProjekt().getProjektId(),pageable).getContent());
            model.addAttribute("projektId",zadanieEdit.getProjekt().getProjektId());
            model.addAttribute("size",10);
            model.addAttribute("page",0);
            model.addAttribute("nextPage",1);
            model.addAttribute("previousPage",0);
            return "zadaniaList?projektId="+zadanieEdit.getProjekt().getProjektId();
        }

        model.addAttribute("zadania", zadanieService
                .getZadanieProjektu(zadanieEdit.getProjekt().getProjektId(),pageable).getContent());
        model.addAttribute("projektId",zadanieEdit.getProjekt().getProjektId());
        model.addAttribute("size",10);
        model.addAttribute("page",0);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);

        return "redirect:/zadaniaList?projektId="+zadanieEdit.getProjekt().getProjektId();
    }

    @PostMapping ("/zadanieZmienKolejnosc")
    public String zadanieUsun(Model model, Integer zadanieId, Integer projektId, Pageable pageable, String upDown
            , BindingResult bindingResult){

        List<Zadanie> zadaniaProjektu = projektService.getProjekt(projektId).get().getZadania();
        if(upDown.equals("UP")){
            Zadanie zadanie1 = zadaniaProjektu.stream().filter(z->z.getZadanieId().equals(zadanieId)).findFirst().get();
            System.out.println(zadaniaProjektu.size());
            if(zadanie1.getKolejnosc()<zadaniaProjektu.size()) {

                Zadanie zadanie2 = zadaniaProjektu.stream().filter(z->z.getKolejnosc()==zadanie1.getKolejnosc()+1).findFirst().get();
                zadanie2.setKolejnosc(zadanie2.getKolejnosc() - 1);
                zadanie1.setKolejnosc(zadanie1.getKolejnosc() + 1);
                zadanieService.setZadanie(zadanie1,projektId);
                zadanieService.setZadanie(zadanie2,projektId);
            }
        }else{
            Zadanie zadanie1 = zadaniaProjektu.stream().filter(z->z.getZadanieId().equals(zadanieId)).findFirst().get();
            if(zadanie1.getKolejnosc()>1) {

                Zadanie zadanie2 = zadaniaProjektu.stream().filter(z->z.getKolejnosc()==zadanie1.getKolejnosc()-1).findFirst().get();
                zadanie2.setKolejnosc(zadanie2.getKolejnosc() + 1);
                zadanie1.setKolejnosc(zadanie1.getKolejnosc() - 1);
                zadanieService.setZadanie(zadanie1,projektId);
                zadanieService.setZadanie(zadanie2,projektId);
            }
        }

        model.addAttribute("zadania", zadanieService.getZadanieProjektu(projektId,pageable).getContent());
        model.addAttribute("projektId",projektId);
        model.addAttribute("size",10);
        model.addAttribute("page",0);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "zadaniaList";
    }

}
