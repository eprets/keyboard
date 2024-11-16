package com.example.keyboardtrainer.models;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise_categories")
public class ExerciseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Например, "Легкий", "Средний", "Сложный"

    // Конструктор без параметров
    public ExerciseCategory() {
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
