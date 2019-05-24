package com.moodle.sevsu.webdb.repository;

import com.moodle.sevsu.webdb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}