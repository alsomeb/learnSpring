package com.alsomeb.learnspring.controller;


import com.alsomeb.learnspring.model.Todo;
import com.alsomeb.learnspring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/todo") // localhost:8080/api/todo/
public class TodoController {

    // CONTROLLER SKALL BARA PRATA MED SERVICE, INTE REPOSITORY (DB LAYER)
    // SÅ VI KÖR BARA SERVICE METODERNA I CONTROLLER (BUSINESS LOGIC = service) VI DEFINERAR DEM INTE HÄR
    //Controllern ROUTAR bara requests. Kör endast metoderna i Service. Servicen är själva "hjärnan"
    //Servicen pratar sedan med repositoryn som i sin tur ansvarar för att prata med databasen

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // GET ALL, localhost:8080/api/todo/
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    //https://howtodoinjava.com/spring5/webmvc/controller-getmapping-postmapping/#3-spring-postmapping-example
    // GET BY ID, localhost:8080/api/todo/id
    @GetMapping("{id}")
    public ResponseEntity<Todo> findById(@PathVariable Long id) { // tex id 1337 - localhost:8080/api/todo/1337
        Optional<Todo> todo = todoService.findById(id);

        // map return object 200 OK if present or else HTTP 404 not found
        return todo.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST  ( SKAPA) localhost:8080/api/todo/
    // Behöver inte returnera hela User objekt, det kan en GET request göra, return bara ID
    @PostMapping
    public ResponseEntity<Long> addTodo(@RequestBody Todo todo) { //kolla upp @RequestBody
        return new ResponseEntity<>(todoService.add(todo), HttpStatus.CREATED);
    }

    // Update ( Not passing ID ) - but will return ID and 200 if match else custom exception with 404 code
    // no need to return full object that can a GET req do.
    // Logic in ServiceImpl
    // Will save to existing todo or create a new one! 2 flugor 1 smäll
    @PutMapping
    public ResponseEntity<Long> updateTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.update(todo), HttpStatus.OK);
    }

   @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        todoService.deleteById(id); // kommer generera 200 OK om det funkar annars 500
   }
}
