package com.moodle.sevsu.webdb.repository;

import com.moodle.sevsu.webdb.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
}
