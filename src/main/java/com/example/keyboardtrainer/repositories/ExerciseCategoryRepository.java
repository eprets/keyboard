package com.example.keyboardtrainer.repositories;

import com.example.keyboardtrainer.models.ExerciseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, Long> {
    ExerciseCategory findByName(String name);
}