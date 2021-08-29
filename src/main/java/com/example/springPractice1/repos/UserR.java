package com.example.springPractice1.repos;

import com.example.springPractice1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserR extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
