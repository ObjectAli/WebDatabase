package com.moodle.sevsu.webdb.controllers;

import com.moodle.sevsu.webdb.Service.CourseService;
import com.moodle.sevsu.webdb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Controller
@RequestMapping("/")
public class DiagramsController {

    private CourseService courseService;

    private UserService userService;

    @Autowired
    public void setCourseService(CourseService service) {
        this.courseService = service;
    }

    @Autowired
    public void setUserService(UserService service) {
        this.userService = service;
    }

    @GetMapping("/diagrams")
    public String diagramReadness(Model model){
        //Сourse readiness chart
        Map<String,Integer> map = courseService.findCourse();
        model.addAttribute("map",map);

        //Count of courses in readiness chart
        int readness1 = courseService.countReadness(0,30);
        int readness2 = courseService.countReadness(30,70);
        int readness3 = courseService.countReadness(70, 99);
        int readness4 = courseService.countReadness(99, 100);

        model.addAttribute("readness1", readness1);
        model.addAttribute("readness2", readness2);
        model.addAttribute("readness3", readness3);
        model.addAttribute("readness4", readness4);

        //Count of courses in each direction of training
        HashMap<String, AtomicInteger> mapCount = courseService.findCountCourseOfDir();
        model.addAttribute("mapCount",mapCount);

        //Count of position with users
        AtomicInteger postion1 = userService.findCountPosition("Бакалавр");
        AtomicInteger postion2 = userService.findCountPosition("Специалист");
        AtomicInteger postion3 = userService.findCountPosition("Магистр");
        AtomicInteger postion4 = userService.findCountPosition("Аспирант");
        AtomicInteger postion5 = userService.findCountPosition("Ассистент");
        AtomicInteger postion6 = userService.findCountPosition("Ст. преподаватель");
        AtomicInteger postion7 = userService.findCountPosition("Доцент");
        AtomicInteger postion8 = userService.findCountPosition("Профессор");

        model.addAttribute("postion1", postion1);
        model.addAttribute("postion2", postion2);
        model.addAttribute("postion3", postion3);
        model.addAttribute("postion4", postion4);
        model.addAttribute("postion5", postion5);
        model.addAttribute("postion6", postion6);
        model.addAttribute("postion7", postion7);
        model.addAttribute("postion8", postion8);

        return "diagrams";
    }
}
