package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface CourseService {

    Course getCourseById(Integer id);

    void saveCourse(Course course);

    void updateCourse(Integer id, String title, int hours, int eee, int readiness);

    void deleteCourse(Integer id);

    List<Course> findAll();

    List<Direction> findAllDirection();

    List<User> findAllUser();

    List<Developer> findDevelopersById(int devId);

    List<CourseDir> findDirectionsById(int dirId);

    HashMap<String,Integer> findCourse();

    HashMap<String, AtomicInteger> findCountCourseOfDir();

    int countReadness(int a, int b);
}
