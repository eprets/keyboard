package com.example.keyboardtrainer.repositories;

import com.example.keyboardtrainer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);  // Метод для проверки уникальности email
}
