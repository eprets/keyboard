package com.example.keyboardtrainer.services;

import com.example.keyboardtrainer.models.User;
import com.example.keyboardtrainer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Загружаем пользователя из базы данных
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        // Возвращаем пользователя, и Spring Security будет использовать этот объект для аутентификации
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())  // Уже закодированный пароль из базы данных
                .roles("USER")  // Указываем роль пользователя
                .build();
    }
}
