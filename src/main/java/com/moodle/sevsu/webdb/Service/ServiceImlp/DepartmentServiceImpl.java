package com.moodle.sevsu.webdb.Service.ServiceImlp;

import com.moodle.sevsu.webdb.Service.DepartmentService;
import com.moodle.sevsu.webdb.entity.Department;
import com.moodle.sevsu.webdb.entity.Institute;
import com.moodle.sevsu.webdb.repository.DepartmentRepository;
import com.moodle.sevsu.webdb.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository repository;

    private InstituteRepository instituteRepository;

    @Autowired
    public void setInstituteRepository(InstituteRepository repository){this.instituteRepository = repository;}

    @Autowired
    public void setDepartmentRepository(DepartmentRepository repository){this.repository = repository;}

    @Override
    public Department getDepartmentById(Integer id){return  repository.getOne(id);}

    @Override
    public void saveDepartment(Department department){repository.save(department);}

    @Override
    public void updateDepartment(Integer id, String title){
        Department updated = repository.getOne(id);
        updated.setTitle(title);
        repository.save(updated);
    }

    @Override
    public void deleteDepartment(Integer id){repository.deleteById(id);}

    @Override
    public List<Department> findAll(){return repository.findAll();}

    @Override
    public List<Institute> findAllInstitute(){return instituteRepository.findAll();}

}
