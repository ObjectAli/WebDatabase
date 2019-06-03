package com.moodle.sevsu.webdb.controllers;


import com.moodle.sevsu.webdb.Service.CourseService;
import com.moodle.sevsu.webdb.Service.ExportExcelService;
import com.moodle.sevsu.webdb.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/")
public class CourseController {

    private CourseService courseService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ExportExcelService exportExcelService;

    @Autowired
    public void setCourseService(CourseService service) {
        this.courseService = service;
    }

    @Autowired
    public void setExportExcelService(ExportExcelService service) {
        this.exportExcelService = service;
    }

    @GetMapping("/courses")
    public String list(Model model) {
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("directions", courseService.findAllDirection());
        model.addAttribute("users", courseService.findAllUser());
        return "courses";
    }

    @PostMapping("/courses/new")
    public ModelAndView updateCourse(@RequestParam String title, @RequestParam int hours, @RequestParam int eee, @RequestParam int readiness) {
        courseService.saveCourse(new Course(title, hours, eee, readiness));
        return new ModelAndView(new RedirectView("/courses"));
    }

    @GetMapping("/courses/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "coursesFunc/edit";
    }

    @PostMapping("/courses/update")
    public ModelAndView saveCourse(@RequestParam Integer id, @RequestParam String title, @RequestParam int hours, @RequestParam int eee, @RequestParam int readiness) {
        courseService.updateCourse(id, title, hours, eee, readiness);
        return new ModelAndView(new RedirectView("/courses"));
    }

    @GetMapping("/courses/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return new ModelAndView(new RedirectView("/courses"));
    }

    @GetMapping("/courses/findDev/{id}")
    public String listDevelopers(@PathVariable Integer id, Model model) {
        model.addAttribute("developers", courseService.findDevelopersById(id));
        return "coursesFunc/dev";
    }

    @GetMapping("/courses/findDir/{id}")
    public String listDirections(@PathVariable Integer id, Model model) {
        model.addAttribute("coursedirs", courseService.findDirectionsById(id));
        return "coursesFunc/dir";
    }

    @GetMapping("/courses/createExcel")
    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response){
        List<Course> list = courseService.findAll();
        boolean isFlag = exportExcelService.createExcel(list, servletContext);
        if(isFlag){
            String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"courses"+".xls");
            exportExcelService.fileDownload(fullPath, response, "courses.xls");
        }
        return new ModelAndView(new RedirectView("/courses"));
    }

}
