package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department getDepartmentById(Integer id);

    void saveDepartment(Department department);

    void updateDepartment(Integer id, String title);

    void deleteDepartment(Integer id);

    List<Department> findAll();
}
