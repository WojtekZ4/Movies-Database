package project.movies.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.movies.models.Movie;
import project.movies.models.Person;
import project.movies.services.MovieService;
import project.movies.services.PersonService;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PutMapping
    public ResponseEntity<Person> createOrUpdatePerson(@RequestBody Person newPerson) {
        return new ResponseEntity<>(personService.savePerson(newPerson), HttpStatus.OK);
    }

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Collection<Person>> getMovies() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-name")
    public ResponseEntity<Person> byName(@RequestParam String name) {
        return new ResponseEntity<>(personService.findOneByName(name), HttpStatus.OK);
    }

    @PutMapping("/actor")
    public ResponseEntity<String> addActedInRelation(@RequestParam String name, @RequestParam String title) {
        personService.addActedInRelation(name, title);
        return new ResponseEntity<>("Relation successfully added.", HttpStatus.OK);
    }

    @PutMapping("/director")
    public ResponseEntity<String> addDirectedRelation(@RequestParam String name, @RequestParam String title) {
        personService.addDirectedRelation(name, title);
        return new ResponseEntity<>("Relation successfully added.", HttpStatus.OK);
    }

    @DeleteMapping("/actor")
    public ResponseEntity<String> deleteDirectedRelation(@RequestParam String name, @RequestParam String title) {
        personService.deleteActedInRelation(name, title);
        return new ResponseEntity<>("Relation successfully deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/director")
    public ResponseEntity<String> deleteActedInRelation(@RequestParam String name, @RequestParam String title) {
        personService.deleteDirectedRelation(name, title);
        return new ResponseEntity<>("Relation successfully deleted.", HttpStatus.OK);
    }

    @GetMapping("/actors")
    public ResponseEntity<Set<Person>> getMovieActors(@RequestParam String title) {
        return new ResponseEntity<>(personService.getMovieActors( title), HttpStatus.OK);
    }

    @GetMapping("/directors")
    public ResponseEntity<Set<Person>> getMovieDirectors(@RequestParam String title) {
        return new ResponseEntity<>(personService.getMovieDirectors(title), HttpStatus.OK);
    }

    @GetMapping("/coActors")
    public ResponseEntity<Set<Person>> getCoActors(String name) {
        return new ResponseEntity<>(personService.getCoActors(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable String id) {
        personService.deleteById(id);
        return new ResponseEntity<>("Movie successfully deleted.", HttpStatus.OK);
    }
}
