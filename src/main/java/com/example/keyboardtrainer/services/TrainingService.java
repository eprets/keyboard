package com.example.keyboardtrainer.services;

import com.example.keyboardtrainer.models.Exercise;
import com.example.keyboardtrainer.models.ExerciseCategory;
import com.example.keyboardtrainer.models.ExerciseType;
import com.example.keyboardtrainer.models.Level;
import com.example.keyboardtrainer.repositories.ExerciseCategoryRepository;
import com.example.keyboardtrainer.repositories.ExerciseRepository;
import com.example.keyboardtrainer.repositories.LevelRepository;
import com.example.keyboardtrainer.repositories.ExerciseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private ExerciseCategoryRepository categoryRepository;

    @Autowired
    private ExerciseTypeRepository typeRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public ExerciseCategory getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public ExerciseType getTypeById(Long typeId) {
        return typeRepository.findById(typeId).orElse(null);
    }

    public List<Exercise> getExercisesByCategoryAndType(ExerciseCategory category, ExerciseType type, Level level) {
        return exerciseRepository.findByCategoryAndTypeAndLevel(category, type, level);
    }

    public Exercise getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId).orElse(null);
    }

    public List<ExerciseCategory> getCategories() {
        List<ExerciseCategory> categories = categoryRepository.findAll();
        // Печать категорий в консоль для отладки
        categories.forEach(category -> System.out.println("Category: " + category.getName()));
        return categories;
    }

    public List<ExerciseType> getTypes() {
        List<ExerciseType> types = typeRepository.findAll();
        // Печать типов в консоль для отладки
        types.forEach(type -> System.out.println("Type: " + type.getName()));
        return types;
    }
}
