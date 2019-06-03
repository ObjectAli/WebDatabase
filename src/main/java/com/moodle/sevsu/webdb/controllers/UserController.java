package com.moodle.sevsu.webdb.controllers;

import com.moodle.sevsu.webdb.Service.DepartmentService;
import com.moodle.sevsu.webdb.Service.ExportExcelService;
import com.moodle.sevsu.webdb.Service.UserService;
import com.moodle.sevsu.webdb.entity.Department;
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
public class UserController {

    private UserService userService;

    private DepartmentService departmentService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ExportExcelService exportExcelService;

    @Autowired
    public void setUserService(UserService service) {
        this.userService = service;
    }

    @Autowired
    public void setDepartmentService(DepartmentService service) {
        this.departmentService = service;
    }

    @Autowired
    public void setExportExcelService(ExportExcelService service) {
        this.exportExcelService = service;
    }

    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("departments", userService.findAllDepartment());
        return "users";
    }

    @PostMapping("/users/new")
    public ModelAndView updateUser(@RequestParam String surname, @RequestParam String name, @RequestParam String secondname, @RequestParam String position, @RequestParam Department department, @RequestParam String phone, @RequestParam String email) {
        userService.saveUser(new User(surname, name, secondname, position, department, phone, email));
        return new ModelAndView(new RedirectView("/users"));
    }

    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("departments", userService.findAllDepartment());
        return "usersFunc/edit";
    }

    @PostMapping("/users/update")
    public ModelAndView saveDirection(@RequestParam Integer id, @RequestParam String surname, @RequestParam String name, @RequestParam String secondname, @RequestParam String position, @RequestParam Department department, @RequestParam String phone, @RequestParam String email) {
        userService.updateUser(id, surname, name, secondname, position, department, phone, email);
        return new ModelAndView(new RedirectView("/users"));
    }

    @GetMapping("/users/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ModelAndView(new RedirectView("/users"));
    }

//    @GetMapping("/users/createExcel")
//    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response){
//        List<User> list = userService.findAll();
//        boolean isFlag = exportExcelService.createExcel(list, servletContext);
//        if(isFlag){
//            String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"users"+".xls");
//            exportExcelService.fileDownload(fullPath, response, "users.xls");
//        }
//        return new ModelAndView(new RedirectView("/users"));
//    }

}
