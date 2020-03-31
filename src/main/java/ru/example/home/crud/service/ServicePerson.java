package ru.example.home.crud.service;

import ru.example.home.crud.entity.Person;

import java.util.List;

public interface ServicePerson {

    List<Person> getAllPersons();
    Person findPersonById(String id);
    Person addPerson(Person id);
    Person updatePerson(Person person);
    void deletePerson(String id);




}
