package com.alsomeb.learnspring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
public class TestController {

    // Path Variable
    @GetMapping("{name}")
    public Person personPathVariable(@PathVariable String name) {
        return new Person(name);
    }

    // Request Parameter given in URL (http://localhost:8080/api/test?name={name})
    @GetMapping
    public Person personRequestParameter(@RequestParam String name) {
        return new Person(name);
    }

    // Static Parameter
    // Request Parameter given in URL (http://localhost:8080/api/test?name=alex) will return specific Person bean
    @GetMapping(params = "name=alex")
    public Person personStaticRequestParameter() {
        return new Person("Alexander Brun");
    }
}

record Person (String name) {}