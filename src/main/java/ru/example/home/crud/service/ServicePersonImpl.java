package ru.example.home.crud.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.home.crud.entity.Person;
import ru.example.home.crud.exception.service.PersonNotFoundException;
import ru.example.home.crud.repo.RepositoryPerson;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)

@NoArgsConstructor
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
                    .of(repositoryPerson.save(person))
                    .orElseThrow(() -> new PersonNotFoundException("update Person", person));
        }
        throw new PersonNotFoundException("update Person is null");

    }

    @Override
    public void deletePerson(String  id) {
        if (id != null) {
            repositoryPerson
                    .deleteById(Optional
                            .of(id)
                            .orElseThrow(() -> new PersonNotFoundException("delete Person", id)));

        } else {
            throw new PersonNotFoundException("delete Person is null");
        }

    }
}
