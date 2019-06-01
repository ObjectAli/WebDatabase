package com.moodle.sevsu.webdb.Service.ServiceImlp;

import com.moodle.sevsu.webdb.Service.CourseDirService;
import com.moodle.sevsu.webdb.entity.*;
import com.moodle.sevsu.webdb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDirServiceImpl implements CourseDirService {

    private CourseDirRepository courseDirRepository;

    private CourseRepository courseRepository;

    private DirectionRepository directionRepository;

    @Autowired
    public void setCourseDirRepository(CourseDirRepository repository){this.courseDirRepository = repository;}

    @Autowired
    public void setDirectionRepository(DirectionRepository repository){this.directionRepository = repository;}

    @Autowired
    public void setCourseRepository(CourseRepository repository){this.courseRepository = repository;}

    @Override
    public CourseDir getCourseDirById(Integer id){return courseDirRepository.getOne(id);}

    @Override
    public void saveCourseDir(CourseDir courseDir){courseDirRepository.save(courseDir);}

    @Override
    public void updateCourseDir(Integer id, Course course, Direction direction){
        CourseDir updated = courseDirRepository.getOne(id);
        updated.setCourse(course);
        updated.setDirection(direction);
        courseDirRepository.save(updated);
    }

    @Override
    public void deleteCourseDir(Integer id){courseDirRepository.deleteById(id);
    }

    @Override
    public List<CourseDir> findAll(){return courseDirRepository.findAll();}

    @Override
    public List<Course> findAllCourse(){return courseRepository.findAll();}

    @Override
    public List<Direction> findAllDirection(){return directionRepository.findAll();}


}
