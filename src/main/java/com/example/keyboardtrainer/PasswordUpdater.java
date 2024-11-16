/*package com.example.keyboardtrainer;

import com.example.keyboardtrainer.models.User;
import com.example.keyboardtrainer.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUpdater implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordUpdater(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Получаем пользователя из базы данных
        User user = userRepository.findByUsername("admin");

        if (user != null) {
            // Зашифровываем пароль
            String encodedPassword = passwordEncoder.encode("привет");

            // Обновляем пароль в объекте пользователя
            user.setPassword(encodedPassword);

            // Сохраняем изменения в базе данных
            userRepository.save(user);
            System.out.println("Пароль для user1 обновлен.");
        }
    }
}
*/