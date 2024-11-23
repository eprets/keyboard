
package com.example.keyboardtrainer.services;

import com.example.keyboardtrainer.models.Result;
import com.example.keyboardtrainer.models.User;
import com.example.keyboardtrainer.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    public List<Result> getResultsByUser(User user) {
        return resultRepository.findByUser(user);
    }
}
