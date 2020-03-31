package ru.example.home.crud.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.home.crud.entity.Person;
import ru.example.home.crud.exception.service.PersonNotFoundException;
import ru.example.home.crud.repo.RepositoryPerson;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServicePersonImplDeleteTest {

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
        person1 = new Person("2", null);
        repositoryPerson.insert(person);
    }

    @After
    public void tearDown() throws Exception {
        repositoryPerson.deleteAll();

    }

    @Test
    public void deletePerson() {
        servicePerson.deletePerson(person.getId());
        assertEquals(servicePerson.getAllPersons().size(),0);
    }
    @Test
    public void deletePersonNull() {

        Exception exception = assertThrows(PersonNotFoundException.class, () -> {
            servicePerson.deletePerson(null);

        });

    }

    @Test
    public void deletePersonNullId() {

        person.setId(null);
        Exception exception = assertThrows(PersonNotFoundException.class, () -> {
            servicePerson.deletePerson(person.getId());

        });

    }
}