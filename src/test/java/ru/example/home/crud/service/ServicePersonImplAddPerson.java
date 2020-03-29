package ru.example.home.crud.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.home.crud.entity.Person;
import ru.example.home.crud.repo.RepositoryPerson;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServicePersonImplAddPerson {

    @Autowired
    private ServicePerson servicePerson;

    @Autowired
    private RepositoryPerson repositoryPerson;

    private Person person;
    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;
    private Person person5;
    private Person person6;

    private Person expected;

    @Before
    public void setUp() throws Exception {
        repositoryPerson.deleteAll();
        person = new Person(null, "name1");
        person1 = new Person("2", null);
        person2 = new Person(null, null);
        person3 = new Person("aaa", "name3");
        person4 = null;
        person5 = new Person("6", "name5");
        person6 = new Person("7", "name6");
    }

    @After
    public void tearDown() throws Exception {
        repositoryPerson.deleteAll();
    }


    @Test
    public void addPerson() {
        expected = servicePerson.addPerson(person);
        Assert.assertEquals(expected, person);
        Assert.assertNotNull(person);
    }

    @Test
    public void addPerson1() {
        expected = servicePerson.addPerson(person1);
        Assert.assertEquals(expected, person1);
    }

    @Test
    public void addPerson2() {
        expected = servicePerson.addPerson(person2);
        Assert.assertEquals(expected, person2);
    }

    @Test
    public void addPerson3() {
        expected = servicePerson.addPerson(person3);
        Assert.assertEquals(expected, person3);
        Assert.assertNotNull(expected);
    }

    @Test
    public void addPerson4() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            expected = servicePerson.addPerson(person4);
        });

    }

    @Test
    public void addPerson5() {
        expected = servicePerson.addPerson(person5);
        Assert.assertEquals(expected, person5);
        Assert.assertNotNull(expected);

    }

    @Test
    public void addPerson6() {
        expected = servicePerson.addPerson(person6);
        Assert.assertEquals(expected, person6);
        Assert.assertNotNull(expected);
    }

}