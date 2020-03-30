package ru.example.home.crud.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.example.home.crud.entity.Person;

@Repository
public interface RepositoryPerson extends MongoRepository<Person, String> {
}
