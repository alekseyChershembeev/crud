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
public class ServicePersonFindPersonByIDImplTest {

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
        person1 = new Person("id2", "name2");
        repositoryPerson.insert(person);
        repositoryPerson.insert(person1);
    }

    @After
    public void tearDown() throws Exception {
        repositoryPerson.deleteAll();
    }

    @Test
    public void findPersonById() {
        assertEquals(servicePerson.findPersonById("id1"), person);
    }

    @Test
    public void findPersonByIdNull() {
        Exception exception = assertThrows(PersonNotFoundException.class, () -> {
            servicePerson.findPersonById("id3");
        });
    }
}