package com.alsomeb.learnspring.service;

import com.alsomeb.learnspring.model.Todo;
import com.alsomeb.learnspring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    // HÄR PRATAR VI MED REPO, DB LAYER
    // DEP INJECTION för att kunna använda repo
    // BUSINESS LOGIC HÄR! INTE I CONTROLLER

    private final TodoRepository repository;


    @Autowired
    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Long add(Todo todo) {
        return repository.save(todo).getId();
    }

    @Override
    public Todo update(Todo todo) {
        todo.setLastUpdate(LocalDate.now());
        return repository.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
