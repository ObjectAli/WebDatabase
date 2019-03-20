package com.moodle.sevsu.webdb.controllers;

import com.moodle.sevsu.webdb.Service.DirectionService;
import com.moodle.sevsu.webdb.entity.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class DirectionController {

    private DirectionService directionService;

    @Autowired
    public void setNoteService(DirectionService service) {
        this.directionService = service;
    }


    @GetMapping("/directions")
    public String list(Model model) {
        model.addAttribute("directions", directionService.findAll());
        return "directions";
    }

    @GetMapping("/new")
    public String newDirection() {
        return "operations/new";
    }

    @PostMapping("/save")
    public String updateDirection(@RequestParam String cipher, String title) {
        directionService.saveDirection(new Direction(cipher,title));
        return "/directions";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Direction direction = directionService.getDirectionById(id);
        model.addAttribute("direction", direction);
        return "operations/edit";
    }

    @PostMapping("/update")
    public String saveDirection(@RequestParam Integer id, @RequestParam String cipher, @RequestParam String title) {
        directionService.updateDirection(id, cipher, title);
        return "/directions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        directionService.deleteDirection(id);
        return "/directions";
    }

}
