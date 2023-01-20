package com.alsomeb.learnspring.service;

import com.alsomeb.learnspring.model.Todo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findAll();
    Optional<Todo> findById(Long id);

    Todo add(Todo todo);
}
