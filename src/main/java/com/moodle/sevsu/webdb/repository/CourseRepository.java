package com.moodle.sevsu.webdb.repository;

import com.moodle.sevsu.webdb.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
