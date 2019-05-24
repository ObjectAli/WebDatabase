package com.moodle.sevsu.webdb.controllers;


import com.moodle.sevsu.webdb.Service.InstituteService;
import com.moodle.sevsu.webdb.entity.Institute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/")
public class InstituteController {

    private InstituteService instituteService;

    @Autowired
    public void setInstituteService(InstituteService service) {
        this.instituteService = service;
    }


    @GetMapping("/institutes")
    public String list(Model model) {
        model.addAttribute("institutes", instituteService.findAll());
        return "institutes";
    }

    @PostMapping("/institutes/new")
    public ModelAndView updateInstitute(@RequestParam String title) {
        instituteService.saveInstitute(new Institute(title));
        return new ModelAndView(new RedirectView("/institutes"));
    }

    @GetMapping("/institutes/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Institute institute = instituteService.getInstituteById(id);
        model.addAttribute("institute", institute);
        return "institutesFunc/edit";
    }

    @PostMapping("/institutes/update")
    public ModelAndView saveInstitute(@RequestParam Integer id, @RequestParam String title) {
        instituteService.updateInstitute(id,  title);
        return new ModelAndView(new RedirectView("/institutes"));
    }

    @GetMapping("/institutes/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        instituteService.deleteInstitute(id);
        return new ModelAndView(new RedirectView("/institutes"));
    }
}
