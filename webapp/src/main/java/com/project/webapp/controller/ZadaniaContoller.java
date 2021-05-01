package com.project.webapp.controller;

import com.project.webapp.service.ZadanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ZadaniaContoller {

    private ZadanieService zadanieService;

    @Autowired
    public ZadaniaContoller(ZadanieService zadanieService){
        this.zadanieService = zadanieService;
    }

}
