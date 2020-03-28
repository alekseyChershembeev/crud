package ru.example.home.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.home.crud.entity.Person;
import ru.example.home.crud.exception.PersonNotFoundException;
import ru.example.home.crud.repo.RepositoryPerson;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePersonImpl implements ServicePerson {

    private RepositoryPerson repositoryPerson;

    @Autowired
    public ServicePersonImpl(RepositoryPerson repositoryPerson) {
        this.repositoryPerson = repositoryPerson;
    }


    @Override
    public List<Person> getAllPersons() {
        return Optional
                .of(repositoryPerson.findAll())
                .orElseThrow(() -> new PersonNotFoundException("from get All Persons"));
    }

    @Override
    public Person findPersonById(String id) {
        return repositoryPerson
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException("from find Person ById", id));


    }

    @Override
    public Person addPerson(Person person) {
        return Optional
                .of(repositoryPerson.insert(person))
                .orElseThrow(() -> new PersonNotFoundException("from add Person", person));
    }

    @Override
    public Person updatePerson(Person person) {
        return Optional
                .of(repositoryPerson.insert(person))
                .orElseThrow(() -> new PersonNotFoundException("update Person", person));
    }

    @Override
    public void deletePerson(Person person) {

    }
}
