package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Department;
import com.moodle.sevsu.webdb.entity.Institute;

import java.util.List;

public interface DepartmentService {

    Department getDepartmentById(Integer id);

    void saveDepartment(Department department);

    void updateDepartment(Integer id, String title, Institute institute);

    void deleteDepartment(Integer id);

    List<Department> findAll();

    List<Institute> findAllInstitute();
}
