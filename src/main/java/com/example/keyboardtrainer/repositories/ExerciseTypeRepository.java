package com.example.keyboardtrainer.repositories;

import com.example.keyboardtrainer.models.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {
    ExerciseType findByName(String name);
}