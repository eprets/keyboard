package com.example.keyboardtrainer.services;

import com.example.keyboardtrainer.models.Progress;
import com.example.keyboardtrainer.repositories.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    // Метод для сохранения прогресса
    public void saveProgress(Long userId, Long exerciseId, Long categoryId, Long typeId, Long levelId, int errors, int completionTime) {
        Progress progress = new Progress();
        progress.setUserId(userId);
        progress.setExerciseId(exerciseId);
        progress.setCategoryId(categoryId);
        progress.setTypeId(typeId);
        progress.setLevelId(levelId);
        progress.setErrors(errors);
        progress.setCompletionTime(completionTime);
        progressRepository.save(progress);
    }

    // Метод для получения прогресса по ID пользователя
    public List<Progress> getProgressByUserId(Long userId) {
        return progressRepository.findByUserId(userId);
    }
}
