package ru.example.home.crud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.example.home.crud.entity.Person;
import ru.example.home.crud.service.ServicePerson;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The type Web mock test for check http full Request.
 */
@WebMvcTest(MainController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicePerson service;

    @Test
    public void getPersonShouldReturnMessageFromService() throws Exception {
        when(service.findPersonById("id1"))
                .thenReturn(new Person("id1", "name1"));
        mockMvc
                .perform(get("/persons/id1").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("name1")));
    }

    @Test
    public void getPersonShouldReturnMessageFromServiceNull() throws Exception {
        when(service.findPersonById("id11"))
                .thenReturn(null);
        mockMvc
                .perform(get("/persons/id11")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllPersonsShouldReturnMessageFromService() throws Exception {
        when(service.getAllPersons())
                .thenReturn(Stream
                        .of(new Person("id1", "name1"), new Person("id2", "name2"))
                        .collect(Collectors.toList()));
        String body = "[{\"id\":\"id1\",\"name\":\"name1\"},{\"id\":\"id2\",\"name\":\"name2\"}]";

        this.mockMvc.perform(get("/persons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString(body)));
    }

    @Test
    public void getAllPersonsShouldReturnMessageFromServiceEmpty() throws Exception {
        when(service.getAllPersons())
                .thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/persons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("[]")));
    }

    @Test
    public void addPersonShouldReturnMessageFromService() throws Exception {

        Person person = new Person("id1","name1");
        when(service.addPerson(person))
                .thenReturn(person);

        String body = "{\"id\":\"id1\",\"name\":\"name1\"}";

        this.mockMvc.perform(post("/persons")
                .contentType(APPLICATION_JSON_UTF8)
                .content(body)
        )
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString(body)));
    }
    @Test
    public void updatePersonShouldReturnMessageFromService() throws Exception {

        Person person = new Person("id1","name1");
        when(service.addPerson(person))
                .thenReturn(person);

        person.setName("name2");
        when(service.updatePerson(person))
                .thenReturn(person);

        String body = "{\"id\":\"id1\",\"name\":\"name2\"}";

        this.mockMvc.perform(put("/persons/id1")
                .contentType(APPLICATION_JSON_UTF8)
                .content(body)
        )
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString(body)));
    }

    @Test
    public void deletePersonShouldReturnMessageFromService() throws Exception {

        Person person = new Person("id1","name1");
        service.addPerson(person);

        this.mockMvc.perform(delete("/persons/id1")
                .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }
    
}


