package com.moodle.sevsu.webdb.controllers;

import com.moodle.sevsu.webdb.Service.DepartmentService;
import com.moodle.sevsu.webdb.entity.Department;
import com.moodle.sevsu.webdb.entity.Institute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService service) {
        this.departmentService = service;
    }

    @GetMapping("/departments")
    public String list(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("institutes", departmentService.findAllInstitute());
        return "departments";
    }

    @PostMapping("/departments/new")
    public ModelAndView updateDepartment(@RequestParam String title, @RequestParam Institute institute) {
        departmentService.saveDepartment(new Department(title, institute));
        return new ModelAndView(new RedirectView("/departments"));
    }

    @GetMapping("/departments/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "departmentsFunc/edit";
    }

    @PostMapping("/departments/update")
    public ModelAndView saveDepartment(@RequestParam Integer id, @RequestParam String title) {
        departmentService.updateDepartment(id, title);
        return new ModelAndView(new RedirectView("/departments"));
    }

    @GetMapping("/departments/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return new ModelAndView(new RedirectView("/departments"));
    }
}
