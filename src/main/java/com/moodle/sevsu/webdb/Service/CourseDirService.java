package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Course;
import com.moodle.sevsu.webdb.entity.CourseDir;
import com.moodle.sevsu.webdb.entity.Direction;

import java.util.List;

public interface CourseDirService {

    CourseDir getCourseDirById(Integer id);

    void saveCourseDir(CourseDir courseDir);

    void updateCourseDir(Integer id, Course course, Direction direction);

    void deleteCourseDir(Integer id);

    List<CourseDir> findAll();

    List<Course> findAllCourse();

    List<Direction> findAllDirection();

}
