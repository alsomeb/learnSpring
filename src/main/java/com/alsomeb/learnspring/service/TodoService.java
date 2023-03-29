package com.alsomeb.learnspring.service;

import com.alsomeb.learnspring.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findAll();
    Optional<Todo> findById(Long id);
    Long add(Todo todo);
    Long update(Todo todo);

    void deleteById(Long id);
}
