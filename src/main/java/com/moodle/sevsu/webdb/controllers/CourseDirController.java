package com.moodle.sevsu.webdb.controllers;

import com.moodle.sevsu.webdb.Service.CourseDirService;
import com.moodle.sevsu.webdb.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/")
public class CourseDirController {

    private CourseDirService courseDirService;

    @Autowired
    public void setCourseDirService(CourseDirService service) {
        this.courseDirService = service;
    }

    @GetMapping("/coursedirs")
    public String list(Model model) {
        model.addAttribute("coursedirs", courseDirService.findAll());
        model.addAttribute("courses", courseDirService.findAllCourse());
        model.addAttribute("directions", courseDirService.findAllDirection());
        return "coursedirs";
    }

    @PostMapping("/coursedirs/new")
    public ModelAndView updateCourseDir(@RequestParam Course course, @RequestParam Direction direction) {
        courseDirService.saveCourseDir(new CourseDir(course, direction));
        return new ModelAndView(new RedirectView("/coursedirs"));
    }

    @GetMapping("/coursedirs/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        CourseDir courseDir = courseDirService.getCourseDirById(id);
        model.addAttribute("coursedir", courseDir);
        model.addAttribute("courses", courseDirService.findAllCourse());
        model.addAttribute("directions", courseDirService.findAllDirection());
        return "coursedirsFunc/edit";
    }

    @PostMapping("/coursedirs/update")
    public ModelAndView saveCourseDir(@RequestParam Integer id,@RequestParam Course course, @RequestParam Direction direction) {
        courseDirService.updateCourseDir(id, course, direction);
        return new ModelAndView(new RedirectView("/coursedirs"));
    }

    @GetMapping("/coursedirs/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        courseDirService.deleteCourseDir(id);
        return new ModelAndView(new RedirectView("/coursedirs"));
    }
}
