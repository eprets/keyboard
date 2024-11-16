package com.example.keyboardtrainer.repositories;

import com.example.keyboardtrainer.models.Exercise;
import com.example.keyboardtrainer.models.ExerciseCategory;
import com.example.keyboardtrainer.models.ExerciseType;
import com.example.keyboardtrainer.models.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // Найти упражнения по категории и типу
    List<Exercise> findByCategoryAndTypeAndLevel(ExerciseCategory category, ExerciseType type, Level level);

}
