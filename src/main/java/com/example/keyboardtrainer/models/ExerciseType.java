package com.example.keyboardtrainer.models;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise_types")
public class ExerciseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Например, "Текст", "Цифры"

    // Конструктор без параметров
    public ExerciseType() {
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
