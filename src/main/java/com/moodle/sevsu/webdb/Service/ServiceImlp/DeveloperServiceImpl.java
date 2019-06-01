package com.moodle.sevsu.webdb.Service.ServiceImlp;

import com.moodle.sevsu.webdb.Service.DeveloperService;
import com.moodle.sevsu.webdb.entity.Course;
import com.moodle.sevsu.webdb.entity.Developer;
import com.moodle.sevsu.webdb.entity.User;
import com.moodle.sevsu.webdb.repository.DeveloperRepository;
import com.moodle.sevsu.webdb.repository.CourseRepository;
import com.moodle.sevsu.webdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private DeveloperRepository developerRepository;

    private CourseRepository courseRepository;

    private UserRepository userRepository;

    @Autowired
    public void setDeveloperRepository(DeveloperRepository repository){this.developerRepository = repository;}

    @Autowired
    public void setUserRepository(UserRepository repository){this.userRepository = repository;}

    @Autowired
    public void setCourseRepository(CourseRepository repository){this.courseRepository = repository;}

    @Override
    public Developer getDeveloperById(Integer id){return  developerRepository.getOne(id);}

    @Override
    public void saveDeveloper(Developer developer){developerRepository.save(developer);}

    @Override
    public void updateDeveloper(Integer id, Course course, User user){
        Developer updated = developerRepository.getOne(id);
        updated.setCourse(course);
        updated.setUser(user);
        developerRepository.save(updated);
    }

    @Override
    public void deleteDeveloper(Integer id){developerRepository.deleteById(id);}

    @Override
    public List<Developer> findAll(){return developerRepository.findAll();}

    @Override
    public List<Course> findAllCourse(){return courseRepository.findAll();}

    @Override
    public List<User> findAllUser(){return userRepository.findAll();}

}
