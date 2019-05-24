package com.moodle.sevsu.webdb.Service.ServiceImlp;

import com.moodle.sevsu.webdb.Service.UserService;
import com.moodle.sevsu.webdb.entity.Department;
import com.moodle.sevsu.webdb.entity.User;
import com.moodle.sevsu.webdb.repository.DepartmentRepository;
import com.moodle.sevsu.webdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private DepartmentRepository departmentRepository;

    @Autowired
    public void setProductRepository(UserRepository repository) {
        this.userRepository = repository;
    }

    @Autowired
    public void setUserRepository(DepartmentRepository repository){this.departmentRepository = repository;}

    @Override
    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer id, String name, String surname, String secondname, String position, Department department,String phone, String email) {
        User updated = userRepository.getOne(id);
        updated.setName(name);
        updated.setSurname(surname);
        updated.setSecondname(secondname);
        updated.setPosition(position);
        updated.setDepartment(department);
        updated.setPhone(phone);
        updated.setEmail(email);
        userRepository.save(updated);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Department> findAllDepartment(){return departmentRepository.findAll();}
}
