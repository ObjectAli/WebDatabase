package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Department;
import com.moodle.sevsu.webdb.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface UserService {

    User getUserById(Integer id);

    void saveUser(User user);

    void updateUser(Integer id, String surname, String name, String secondname, String position, Department department, String phone, String email);

    void deleteUser(Integer id);

    List<User> findAll();

    List<Department> findAllDepartment();

    AtomicInteger findCountPosition(String position);

}
