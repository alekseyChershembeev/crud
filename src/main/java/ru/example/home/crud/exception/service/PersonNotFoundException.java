package ru.example.home.crud.exception.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.example.home.crud.entity.Person;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "No such person")

public class PersonNotFoundException extends EntityNotFoundException {


    public PersonNotFoundException() {
        super();
    }

    public PersonNotFoundException(String id) {
        super("Person Id not found: " + id);
    }

    public PersonNotFoundException(Person person) {
        super("Person not found: " + person);
    }

    public PersonNotFoundException(String cause, Person person) {
        super("Person not found: " + " :" + person + cause);
    }

    public PersonNotFoundException(String cause, String id) {
        super("Person not found: " + id + " :" + cause);
    }


}
