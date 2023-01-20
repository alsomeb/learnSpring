package com.alsomeb.learnspring.repository;

import com.alsomeb.learnspring.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

// DATABASE LAYER
/*
What Spring data jpa provides is

- A Repository interface to auto generate most boilerplate query pattern.

 */

public interface TodoRepository extends JpaRepository<Todo, Long> { // <Class, Datatyp>
    // Interfacet + extends gör att man får alla CRUD metoder gratis (man kan define egna också)
    List<Todo> findTodoByCreated(LocalDate created);
}
