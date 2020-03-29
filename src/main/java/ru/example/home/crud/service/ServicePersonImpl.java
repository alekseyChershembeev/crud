package ru.example.home.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.home.crud.entity.Person;
import ru.example.home.crud.exception.PersonNotFoundException;
import ru.example.home.crud.repo.RepositoryPerson;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)

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
        if (id != null) {
            return repositoryPerson
                    .findById(id)
                    .orElseThrow(() -> new PersonNotFoundException("from find Person ById", id));
        }
        throw new PersonNotFoundException("find Person is null");

    }

    @Override
    public Person addPerson(Person person) {
        if (person != null) {
            return Optional
                    .of(repositoryPerson.insert(person))
                    .orElseThrow(() -> new PersonNotFoundException("from add Person", person));
        }

        throw new IllegalArgumentException("add Person is null");
    }

    @Override
    public Person updatePerson(Person person) {

        if (person != null) {
            return Optional
                    .of(repositoryPerson.insert(person))
                    .orElseThrow(() -> new PersonNotFoundException("update Person", person));

        }
        throw new PersonNotFoundException("update Person is null");

    }

    @Override
    public void deletePerson(Person person) {
        if (person != null) {
            repositoryPerson
                    .delete(Optional
                            .of(person)
                            .orElseThrow(() -> new PersonNotFoundException("delete Person", person)));
        }else {
            throw new PersonNotFoundException("delete Person is null");
        }

    }
}
