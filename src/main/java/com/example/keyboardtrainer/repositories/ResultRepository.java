
package com.example.keyboardtrainer.repositories;

import com.example.keyboardtrainer.models.Result;
import com.example.keyboardtrainer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUser(User user);
}
