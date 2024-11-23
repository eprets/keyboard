package com.example.keyboardtrainer.services;

import com.example.keyboardtrainer.models.User;
import com.example.keyboardtrainer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        // Кодируем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Проверка на существование пользователя с таким именем (если необходимо)
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }

        // Проверка на существование пользователя с таким email (если необходимо)
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }

        // Сохраняем пользователя в базе данных
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Пример метода, который может вернуть прогресс пользователя
    public String getProgressForUser(User user) {
        // Пример логики для получения прогресса (например, количество завершенных заданий или тестов)
        // Здесь можно интегрировать логику с вашей базой данных или другой системой
        return "50% завершено";
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
