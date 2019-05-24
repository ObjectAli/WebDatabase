package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Department;
import com.moodle.sevsu.webdb.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Integer id);

    void saveUser(User user);

    void updateUser(Integer id, String name, String surname, String secondname, String position, Department department, String phone, String email);

    void deleteUser(Integer id);

    List<User> findAll();

    List<Department> findAllDepartment();

}
