package com.moodle.sevsu.webdb.controllers;

import com.moodle.sevsu.webdb.Service.DeveloperService;
import com.moodle.sevsu.webdb.Service.ExportExcelService;
import com.moodle.sevsu.webdb.entity.Course;
import com.moodle.sevsu.webdb.entity.Department;
import com.moodle.sevsu.webdb.entity.Developer;
import com.moodle.sevsu.webdb.entity.User;
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
public class DeveloperController {

    private DeveloperService developerService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ExportExcelService exportExcelService;

    @Autowired
    public void setCourseDevService(DeveloperService service) {
        this.developerService = service;
    }

    @GetMapping("/developers")
    public String list(Model model) {
        model.addAttribute("developers", developerService.findAll());
        model.addAttribute("courses", developerService.findAllCourse());
        model.addAttribute("users", developerService.findAllUser());
        return "developers";
    }

    @PostMapping("/developers/new")
    public ModelAndView updateDeveloper(@RequestParam Course course, @RequestParam User user) {
        developerService.saveDeveloper(new Developer(course, user));
        return new ModelAndView(new RedirectView("/developers"));
    }

    @GetMapping("/developers/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Developer developer = developerService.getDeveloperById(id);
        model.addAttribute("developer", developer);
        model.addAttribute("courses", developerService.findAllCourse());
        model.addAttribute("users", developerService.findAllUser());
        return "developersFunc/edit";
    }

    @PostMapping("/developers/update")
    public ModelAndView saveDeveloper(@RequestParam Integer id,@RequestParam Course course, @RequestParam User user) {
        developerService.updateDeveloper(id, course, user);
        return new ModelAndView(new RedirectView("/developers"));
    }

    @GetMapping("/developers/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        developerService.deleteDeveloper(id);
        return new ModelAndView(new RedirectView("/developers"));
    }

    @GetMapping("/developers/createExcel")
    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response){
        List<Developer> list = developerService.findAll();
        boolean isFlag = exportExcelService.createExcel(list, servletContext);
        if(isFlag){
            String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"developers"+".xls");
            exportExcelService.fileDownload(fullPath, response, "developers.xls");
        }
        return new ModelAndView(new RedirectView("/developers"));
    }

}
