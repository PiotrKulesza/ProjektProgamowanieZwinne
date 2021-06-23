package com.project.rest.controller;

import com.project.rest.model.Student;
import com.project.rest.model.Wykladowca;
import com.project.rest.services.WykladowcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WykladowcaRestController {

    private WykladowcaService wykladowcaService;

    @Autowired
    public WykladowcaRestController(WykladowcaService wykladowcaService) {
        this.wykladowcaService = wykladowcaService;
    }

    @GetMapping(value = "/wykladowca/login", params = {"email","haslo"})
    Optional<Wykladowca> getByLogin(@RequestParam String email, @RequestParam String haslo){
        Optional<Wykladowca> optionalWykladowca = wykladowcaService.getByLogin(email,haslo);

        if(optionalWykladowca.isPresent()){
            Wykladowca wykladowca = optionalWykladowca.get();
            wykladowca.setHaslo(null);
            return Optional.of(wykladowca);
        }

        return optionalWykladowca;

    }


}
