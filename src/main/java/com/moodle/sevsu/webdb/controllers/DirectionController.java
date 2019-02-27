package com.moodle.sevsu.webdb.controllers;

import com.moodle.sevsu.webdb.Service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectionController {

    private DirectionService directionService;

    @Autowired
    public void setNoteService(DirectionService service) {
        this.directionService = service;
    }

    @GetMapping("/")
    public String list(Model model) {
        return "directions";
    }

}
