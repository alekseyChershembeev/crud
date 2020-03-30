package ru.example.home.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/persons/{id}")
    public ResponseEntity<Optional<Person>> getPerson(@PathVariable(name = "id") String id) {

        Optional<Person> person = Optional.of(servicePerson.findPersonById(id));

        return person.map(value -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }


//    @PostMapping("/persons")
//    public ResponseEntity<Person> addPerson(@RequestBody Person person) {

//        Book book = repositoryPerson.addBook(BookMapper.(bookDto));

//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(repositoryPerson.addBook(book))
//                .toUri();
//
//        return ResponseEntity.created(uri).build();

//        Book book = BookMapper.bookDTOtoBook(bookDTO);


//        return (person != null)
//                ? new ResponseEntity<Person>((servicePerson.addPerson(person).get()), HttpStatus.OK)
//                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//
//    }
//
//    @PutMapping("/persons/{id}")
//    public ResponseEntity<Person> showBookForEdit(@PathVariable(name = "id") String id, @RequestBody Person person) {


//        Optional<Person> newBook = repositoryPerson.updateBookById(id, book.getTitle(), book.getAuthors(), book.getGenre());
//
//        return newBook.map(value -> new ResponseEntity<>(BookMapper.mapBookToDTO(newBook.get()), HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));


//    }
//
//    @DeleteMapping("/persons/{id}")
//    public ResponseEntity<Void> deleteBook(@PathVariable(name = "id") String id) {
//        return repositoryPerson.deleteBookById(id)
//                ? ResponseEntity.noContent().build()
//                : ResponseEntity.notFound().build();
//
//    }

    }
