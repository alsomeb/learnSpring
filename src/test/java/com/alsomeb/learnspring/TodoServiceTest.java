package com.alsomeb.learnspring;


import com.alsomeb.learnspring.model.Todo;
import com.alsomeb.learnspring.service.TodoService;

import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// https://medium.com/@reachansari/rest-endpoint-testing-with-mockmvc-7b3da1f83fbb
/*
    Trying out Mockito, trying to learn

    @WebMvcTest: This annotation initializes web MVC related configurations
    required to write the JUnit test case for controller classes.

    @MockBean: This annotation creates mocked beans in the
    spring application context.

    -- We have mocked the return values of the service layer in our example.

    jsonPath(): Spring boot provides built-in supports JsonPath that is helpful to verify the JSON response.

    MockMvc returns a result object on calling andReturn(),
    that contains the response details of a particular MVC operation.

 */

@WebMvcTest
@ContextConfiguration(classes = LearnSpringApplication.class)
class TodoServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoService todoservice;



    @Test
    void testGetAllTodosEndpointWorks() throws Exception {
        // Given
        String url = "/api/todo";
        Todo todo = new Todo("Test");
        todo.setId(1L);

        Todo todo2 = new Todo("Test 2");
        todo2.setId(2L);

        List<Todo> todos = List.of(
               todo, todo2
        );

        Mockito.when(todoservice.findAll()).thenReturn(todos);

        // When
        ResultActions response = mockMvc.perform(get(url));

        // Then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @Test
    void getTodoByIdWorks() throws Exception {
        String url = "/api/todo/{id}";
        Todo todo = new Todo("alex");
        todo.setId(2L);
        Mockito.when(todoservice.findById(2L)).thenReturn(Optional.of(todo));

        mockMvc.perform(MockMvcRequestBuilders
              .get(url, 2)
              .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
              .andExpect(MockMvcResultMatchers.jsonPath("$.desc").value("alex"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.created").exists());
    }

    @Test
    void getTodoByWrongIdShouldReturnStatusCode404() throws Exception {
        String url = "/api/todo/{id}";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(url, 2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
