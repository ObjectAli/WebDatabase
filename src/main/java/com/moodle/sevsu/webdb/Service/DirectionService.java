package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Direction;
import com.moodle.sevsu.webdb.entity.Institute;

import java.util.List;

public interface DirectionService {

    Direction getDirectionById(Integer id);

    void saveDirection(Direction direction);

    void updateDirection(Integer id, String cipher, String title, Institute institute);

    void deleteDirection(Integer id);

    List<Direction> findAll();

    List<Institute> findAllInstitute();

}
