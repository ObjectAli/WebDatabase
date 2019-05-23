package com.moodle.sevsu.webdb.Service.ServiceImlp;

import com.moodle.sevsu.webdb.Service.InstituteService;
import com.moodle.sevsu.webdb.entity.Institute;
import com.moodle.sevsu.webdb.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituteServiceImpl implements InstituteService {

    private InstituteRepository repository;

    @Autowired
    public void setInstituteRepository(InstituteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Institute getInstituteById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public void saveInstitute(Institute institute) {
        repository.save(institute);
    }

    @Override
    public void updateInstitute(Integer id, String title) {
        Institute updated = repository.getOne(id);
        updated.setTitle(title);
        repository.save(updated);
    }

    @Override
    public void deleteInstitute(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Institute> findAll() {
        return repository.findAll();
    }
}
