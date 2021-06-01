package com.project.rest.controller;

import com.project.rest.model.Projekt;
import com.project.rest.model.Student;
import com.project.rest.model.Zadanie;
import com.project.rest.services.ProjektService;
import com.project.rest.services.StudentService;
import com.project.rest.services.ZadanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class ZadanieRestController {
    private ZadanieService zadanieService;
    private ProjektService projektService;

    @Autowired
    public ZadanieRestController(ZadanieService zadanieService, ProjektService projektService) {
        this.projektService=projektService;
        this.zadanieService = zadanieService;


    }

    @GetMapping("/zadania/{zadanieId}")
    ResponseEntity<Zadanie> getZadabue(@PathVariable Integer zadanieId) {
        return ResponseEntity.of(zadanieService.getZadanie(zadanieId));
    }

    @PostMapping(path = "/zadania", params = "projektId")
    ResponseEntity<Void> createZadanie(@Valid @RequestBody Zadanie zadanie, Integer projektId) {
        Integer nextKolejnosc= projektService.getProjekt(projektId).get().getZadania().size();
        zadanie.setDataczasDodania(LocalDateTime.now());
        zadanie.setKolejnosc(nextKolejnosc+1);
        zadanie.setProjekt(projektService.getProjekt(projektId).get());
        Zadanie createdZadanie = zadanieService.setZadanie(zadanie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{zadanieId}").buildAndExpand(createdZadanie.getZadanieId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(name="/zadania/{zadanieId}", params = "projektId")
    public ResponseEntity<Void> updateZadanie(@Valid @RequestBody Zadanie zadanie,
                                              @PathVariable Integer zadanieId, Integer projektId) {
        return zadanieService.getZadanie(zadanieId)
                .map(p -> {
                    zadanieService.setZadanie(zadanie);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/zadania/{zadanieId}")
    public ResponseEntity<Void> deleteZadanie(@PathVariable Integer zadanieId) {
        return zadanieService.getZadanie(zadanieId).map(p -> {
            zadanieService.deleteZadanie(zadanieId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping (value = "/zadania", params = "projektId")
    Page<Zadanie> getZadanieProjektu (Integer projektId, Pageable pageable){
        return zadanieService.getZadanieProjektu(projektId,pageable);
    }
}
