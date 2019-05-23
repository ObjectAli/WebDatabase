package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Institute;

import java.util.List;

public interface InstituteService {

    Institute getInstituteById(Integer id);

    void saveInstitute(Institute institute);

    void updateInstitute(Integer id, String title);

    void deleteInstitute(Integer id);

    List<Institute> findAll();
}
