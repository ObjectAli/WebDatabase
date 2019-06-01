package com.moodle.sevsu.webdb.Service.ServiceImlp;

import com.moodle.sevsu.webdb.Service.CourseService;
import com.moodle.sevsu.webdb.entity.*;
import com.moodle.sevsu.webdb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private DirectionRepository directionRepository;

    private UserRepository userRepository;

    private DeveloperRepository developerRepository;

    private CourseDirRepository courseDirRepository;

    @Autowired
    public void setCourseRepository(CourseRepository repository) {
        this.courseRepository = repository;
    }

    @Autowired
    public void setUserRepository(DirectionRepository repository) {
        this.directionRepository = repository;
    }

    @Autowired
    public void setDirectionRepository(UserRepository repository) {
        this.userRepository = repository;
    }

    @Autowired
    public void setDeveloperRepository(DeveloperRepository repository) {
        this.developerRepository = repository;
    }

    @Autowired
    public void setCourseDirRepository(CourseDirRepository repository) {
        this.courseDirRepository = repository;
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.getOne(id);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Integer id, String title, int hours, int eee, int readiness) {
        Course updated = courseRepository.getOne(id);
        updated.setTitle(title);
        updated.setHours(hours);
        updated.setEee(eee);
        updated.setReadiness(readiness);
        courseRepository.save(updated);
    }

    @Override
    public void deleteCourse(Integer id) {
        developerRepository.deleteAll(findDevelopersById(id));
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Direction> findAllDirection() {
        return directionRepository.findAll();
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<Developer> findDevelopersById(int devId) {
        List<Developer> developers = developerRepository.findAll();
        List<Developer> list = new ArrayList<>();
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).getCourse().getId() == devId) {
                list.add(developers.get(i));
            }
        }
        return list;
    }

    @Override
    public List<CourseDir> findDirectionsById(int dirId) {
        List<CourseDir> directions = courseDirRepository.findAll();
        List<CourseDir> list = new ArrayList<>();
        for (int i = 0; i < directions.size(); i++) {
            if (directions.get(i).getCourse().getId() == dirId) {
                list.add(directions.get(i));
            }
        }
        return list;
    }

    @Override
    public HashMap<String,Integer> findCourse(){
        List<Course> courses = courseRepository.findAll();
        HashMap<String,Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < courses.size(); i++){
            map.put(courses.get(i).getTitle(),courses.get(i).getReadiness());
        }
        return map;
    }

    @Override
    public HashMap<String,AtomicInteger> findCountCourseOfDir(){
        List<CourseDir> courseDirs = courseDirRepository.findAll();
        HashMap<String,AtomicInteger> map = new HashMap<String, AtomicInteger>();
        for(CourseDir courseDir: courseDirs){
            map.putIfAbsent(courseDir.getDirection().getTitle(),new AtomicInteger(0));
            map.get(courseDir.getDirection().getTitle()).incrementAndGet();
        }
        return map;
    }

    @Override
    public int countReadness(int a, int b){
        int count = 0;
        List <Course> list = courseRepository.findAll();
        for (int i = 0; i < list.size(); i++){
            if((list.get(i).getReadiness() > a)&&(list.get(i).getReadiness() <= b)){
                count = count + 1;
            }
        }
        return count;
    }

}
