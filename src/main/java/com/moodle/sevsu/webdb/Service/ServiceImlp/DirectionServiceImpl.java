package com.moodle.sevsu.webdb.Service.ServiceImlp;

import com.moodle.sevsu.webdb.Service.DirectionService;
import com.moodle.sevsu.webdb.entity.Direction;
import com.moodle.sevsu.webdb.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectionServiceImpl implements DirectionService {

    private DirectionRepository repository;

    @Autowired
    public void setProductRepository(DirectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Direction getDirectionById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public void saveDirection(Direction direction) {
        repository.save(direction);
    }

    @Override
    public void updateDirection(Integer id, String cipher, String title) {
        Direction updated = repository.getOne(id);
        updated.setCipher(cipher);
        updated.setTitle(title);
        repository.save(updated);
    }

    @Override
    public void deleteDirection(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Direction> findAll() {
        return repository.findAll();
    }

}
