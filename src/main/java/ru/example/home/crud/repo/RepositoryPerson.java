package ru.example.home.crud.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.example.home.crud.entity.Person;

public interface RepositoryPerson extends MongoRepository<Person, String> {
}
