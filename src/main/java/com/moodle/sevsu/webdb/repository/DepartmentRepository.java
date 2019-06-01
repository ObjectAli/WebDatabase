package com.moodle.sevsu.webdb.repository;

import com.moodle.sevsu.webdb.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
