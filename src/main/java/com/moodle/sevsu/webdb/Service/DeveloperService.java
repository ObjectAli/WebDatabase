package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Course;
import com.moodle.sevsu.webdb.entity.Developer;
import com.moodle.sevsu.webdb.entity.User;

import java.util.List;

public interface DeveloperService {

    Developer getDeveloperById(Integer id);

    void saveDeveloper(Developer developer);

    void updateDeveloper(Integer id, Course course, User user);

    void deleteDeveloper(Integer id);

    List<Developer> findAll();

    List<Course> findAllCourse();

    List<User> findAllUser();

}
