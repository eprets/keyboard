package com.example.keyboardtrainer.services;

import com.example.keyboardtrainer.models.Exercise;
import com.example.keyboardtrainer.models.ExerciseCategory;
import com.example.keyboardtrainer.models.ExerciseType;
import com.example.keyboardtrainer.models.Level;
import com.example.keyboardtrainer.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    /**
     * Получить все упражнения.
     *
     * @return список всех упражнений
     */
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    /**
     * Найти упражнение по ID.
     *
     * @param id ID упражнения
     * @return упражнение, если оно существует
     */
    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    /**
     * Найти упражнения по категории и типу.
     *
     * @param category категория упражнения
     * @param type     тип упражнения
     * @return список упражнений, соответствующих категории и типу
     */
    public List<Exercise> getExercisesByCategoryAndType(ExerciseCategory category, ExerciseType type, Level level) {
        return exerciseRepository.findByCategoryAndTypeAndLevel(category, type, level); // Используем метод репозитория для поиска
    }

    /**
     * Создать новое упражнение.
     *
     * @param exercise объект упражнения
     * @return созданное упражнение
     */
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    /**
     * Обновить существующее упражнение.
     *
     * @param exercise объект упражнения
     * @return обновленное упражнение
     */
    public Exercise updateExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    /**
     * Удалить упражнение по ID.
     *
     * @param id ID упражнения
     */
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
