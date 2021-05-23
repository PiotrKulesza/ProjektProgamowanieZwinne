package com.project.webapp.controller;

import javax.validation.Valid;

import com.project.webapp.model.Projekt;
import com.project.webapp.service.ProjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;

@Controller
public class ProjektController {
    private ProjektService projektService;

    @Autowired
    public ProjektController(ProjektService projektService) {
        this.projektService = projektService;
    }

    @GetMapping("/")
    public String index() {
        return "homePage";
    }

    @GetMapping("/projektList")
    public String projektList(Model model, Pageable pageable) {
        model.addAttribute("projekty", projektService.getProjekty(pageable).getContent());
        model.addAttribute("size",10);
        model.addAttribute("page",0);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "projektList";
    }

    @GetMapping("/projektDodaj")
    public String projektDodaj() {

        return "projektDodaj";
    }

    @PostMapping("/projektDodaj")
    public String projektDodaj(@ModelAttribute @Valid Projekt projekt, Model model) {
        projekt.setDataczasUtworzenia(LocalDateTime.now());
        projekt = projektService.setProjekt(projekt);
        model.addAttribute("model",projekt);
        return "projektDodaj";
    }

    @PostMapping("/projektList/delete")
    public String deleteProjekt(Model model, @RequestParam Integer size, @RequestParam String nazwa,
                                @RequestParam Integer page,  @RequestParam Integer projektId) {
        projektService.deleteProjekt(projektId);
        Pageable pageable = PageRequest.of(page,size);
        model.addAttribute("projekty", projektService.searchByNazwa(nazwa,pageable).getContent());
        model.addAttribute("size",size);
        model.addAttribute("page",page);
        model.addAttribute("nazwa",nazwa);
        model.addAttribute("nextPage",page+1);
        if(page==0)model.addAttribute("previousPage",0);
        else model.addAttribute("previousPage",page-1);
        return "projektList";
    }

    @GetMapping("/projektList/search")
    public String searchProjectList(Model model, @RequestParam Integer size, @RequestParam String nazwa) {
        Pageable pageable = PageRequest.of(0,size);
        model.addAttribute("projekty", projektService.searchByNazwa(nazwa,pageable).getContent());
        model.addAttribute("size",size);
        model.addAttribute("page",0);
        model.addAttribute("nazwa",nazwa);
        model.addAttribute("nextPage",1);
        model.addAttribute("previousPage",0);
        return "projektList";
    }
    @GetMapping("/projektList/changePage")
    public String changePageProjectList(Model model, @RequestParam Integer size, @RequestParam String nazwa,
                                        @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page,size);
        model.addAttribute("projekty", projektService.searchByNazwa(nazwa,pageable).getContent());
        model.addAttribute("size",size);
        model.addAttribute("page",page);
        model.addAttribute("nazwa",nazwa);
        model.addAttribute("nextPage",page+1);
        if(page==0)model.addAttribute("previousPage",0);
        else model.addAttribute("previousPage",page-1);
        return "projektList";
    }

    @GetMapping("/projektEdit")
    public String projektEdit(@RequestParam(required = false) Integer projektId, Model model) {
        if(projektId != null) {
            model.addAttribute("projekt", projektService.getProjekt(projektId).get());
        }else {
            Projekt projekt = new Projekt();
            model.addAttribute("projekt", projekt);
        }
        return "projektEdit";
    }

    @PostMapping(path = "/projektEdit")
    public String projektEditSave(@ModelAttribute @Valid Projekt projekt, BindingResult bindingResult) {
//parametr BindingResult powinien wystąpić zaraz za parametrem opatrzonym adnotacją @Valid
        if (bindingResult.hasErrors()) {
            return "projektEdit";
        }
        try {
            projekt = projektService.setProjekt(projekt);
        } catch (HttpStatusCodeException e) {
            bindingResult.rejectValue(null, String.valueOf(e.getStatusCode().value()),
                    e.getStatusCode().getReasonPhrase());
            return "projektEdit";
        }
        return "redirect:/projektList";
    }

    @PostMapping(params="cancel", path = "/projektEdit")
    public String projektEditCancel() {
        return "redirect:/projektList";
    }

    @PostMapping(params="delete", path = "/projektEdit")
    public String projektEditDelete(@ModelAttribute Projekt projekt) {
        projektService.deleteProjekt(projekt.getProjektId());
        return "redirect:/projektList";
    }
}
