package com.example.tmdt.repository;

import com.example.tmdt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByAccount_Id(Long id);
}
