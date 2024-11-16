package com.example.keyboardtrainer.models;

import jakarta.persistence.*;

@Entity
@Table(name = "levels")
public class Level {

    @Id
    private Long id; // Идентификатор уровня
    private String name; // Название уровня (например, "Легкий", "Средний", "Сложный")

    // Конструктор по умолчанию
    public Level() {
    }

    // Конструктор с параметрами
    public Level(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры для id и name
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

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
