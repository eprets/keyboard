package com.example.keyboardtrainer.repositories;

import com.example.keyboardtrainer.models.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    // Поиск прогресса по userId и exerciseId (возвращает одну запись)
    Optional<Progress> findByUserIdAndExerciseId(Long userId, Long exerciseId);

    // Поиск всех записей прогресса по userId (возвращает список)
    List<Progress> findByUserId(Long userId);
}
