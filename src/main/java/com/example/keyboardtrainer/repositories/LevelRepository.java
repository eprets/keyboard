package com.example.keyboardtrainer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.keyboardtrainer.models.Level;
import org.springframework.stereotype.Repository;

public interface LevelRepository extends JpaRepository<Level, Long> {
}
