package com.alsomeb.learnspring.controller;

import com.alsomeb.learnspring.model.Todo;
import com.alsomeb.learnspring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/todo") // localhost:8080/api/todo/
public class TodoController {

    // CONTROLLER SKALL BARA PRATA MED SERVICE, INTE REPOSITORY (DB LAYER)
    // SÅ VI KÖR BARA SERVICE METODERNA I CONTROLLER (BUSINESS LOGIC = service) VI DEFINERAR DEM INTE HÄR
    //Controllern ROUTAR bara requests. Kör endast metoderna i Service. Servicen är själva "hjärnan"
    //Servicen pratar sedan med repositoryn som i sin tur ansvarar för att prata med databasen

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Swagger, when someone req for the root page redirect to swagger UI
    // @ApiIgnore removes all the default predefined HTTP requests, WE WILL DEFINE THEM BY OURSELVES (IN HERE, SWAGGER AUTO DETECT?)
    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/");
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
        if(todo.isPresent()) {
            return new ResponseEntity<>(todo.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST  ( SKAPA) localhost:8080/api/todo/
    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) { //kolla upp @RequestBody
        return new ResponseEntity<>(todoService.add(todo), HttpStatus.CREATED);
    }

    // Update ( Not passing ID )
    // Will save to existing todo or create a new one! 2 flugor 1 smäll
    @PutMapping
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.update(todo), HttpStatus.OK);
    }

    // TODO och Delete samt Tester, (fixa ResponseEntities på allt?)


}
