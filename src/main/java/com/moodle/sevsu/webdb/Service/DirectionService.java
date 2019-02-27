package com.moodle.sevsu.webdb.Service;

import com.moodle.sevsu.webdb.entity.Direction;

import java.util.List;

public interface DirectionService {

    Direction getDirectionById(Integer id);

    void saveDirection(Direction direction);

    void updateDirection(Integer id, String cipher, String title);

    void deleteDirection(Integer id);

    List<Direction> findAll();

}
