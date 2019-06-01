package com.moodle.sevsu.webdb.controllers;

import com.moodle.sevsu.webdb.Service.DirectionService;
import com.moodle.sevsu.webdb.entity.Direction;
import com.moodle.sevsu.webdb.entity.Institute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class DirectionController {

    private DirectionService directionService;

    @Autowired
    public void setDirectionService(DirectionService service) {
        this.directionService = service;
    }


    @GetMapping("/directions")
    public String list(Model model) {
        model.addAttribute("directions", directionService.findAll());
        model.addAttribute("institutes", directionService.findAllInstitute());
        return "directions";
    }

    @PostMapping("/directions/new")
    public ModelAndView updateDirection(@RequestParam String cipher, @RequestParam String title, @RequestParam Institute institute) {
        directionService.saveDirection(new Direction(cipher,title, institute));
        return new ModelAndView(new RedirectView("/directions"));
    }

    @GetMapping("/directions/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Direction direction = directionService.getDirectionById(id);
        model.addAttribute("direction", direction);
        model.addAttribute("institutes", directionService.findAllInstitute());
        return "directionsFunc/edit";
    }

    @PostMapping("/directions/update")
    public ModelAndView saveDirection(@RequestParam Integer id, @RequestParam String cipher, @RequestParam String title, @RequestParam Institute institute) {
        directionService.updateDirection(id, cipher, title, institute);
        return new ModelAndView(new RedirectView("/directions"));
    }

    @GetMapping("/directions/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        directionService.deleteDirection(id);
        return new ModelAndView(new RedirectView("/directions"));
    }
}
