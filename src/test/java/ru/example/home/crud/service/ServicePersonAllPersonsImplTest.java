package ru.example.home.crud.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.home.crud.entity.Person;
import ru.example.home.crud.repo.RepositoryPerson;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServicePersonAllPersonsImplTest {

    @Autowired
    private ServicePerson servicePerson;

    @Autowired
    private RepositoryPerson repositoryPerson;

    private Person person;
    private Person person1;

    @Before
    public void setUp() throws Exception {

        repositoryPerson.deleteAll();
        person = new Person("id1", "name1");
        person1 = new Person("2", "name2");
    }

    @After
    public void tearDown() throws Exception {
        repositoryPerson.deleteAll();
    }

    @Test
    public void getAllPersons() {

        servicePerson.addPerson(person);
        servicePerson.addPerson(person1);
        assertEquals(servicePerson.getAllPersons(),
                Stream.of(person, person1).collect(Collectors.toList()));
    }

    @Test
    public void getAllPersonsEmpty() {
        assertEquals(servicePerson.getAllPersons(),
                new ArrayList<>());
        assertEquals(servicePerson.getAllPersons().size(),
                0);
    }
}