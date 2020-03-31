package ru.example.home.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.home.crud.entity.Person;
import ru.example.home.crud.service.ServicePerson;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class MainController {

    private ServicePerson servicePerson;

    @Autowired
    public MainController(ServicePerson servicePerson) {
        this.servicePerson = servicePerson;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return servicePerson.getAllPersons();
    }

    @GetMapping(value = "/persons/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable(name = "id") String id) {

        Optional<Person> person = Optional.ofNullable(servicePerson.findPersonById(id));

        return person
                .map(value -> new ResponseEntity<>(person.get(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {

        return (person != null)
                ? new ResponseEntity<>((servicePerson.addPerson(person)), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(name = "id") String id, @RequestBody Person person) {
        return (person != null&&id!=null)
                ? new ResponseEntity<>((servicePerson.updatePerson(person)), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
//
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable(name = "id") String id) {
        return (id != null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
