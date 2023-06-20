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
    public Person createOrUpdatePerson(@RequestBody Person newPerson) {
        return personService.savePerson(newPerson);
    }

    @GetMapping(value = { "", "/" })
    Collection<Person> getMovies() {
        return personService.findAll();
    }

    @GetMapping("/by-name")
    Person byName(@RequestParam String name) {
        return personService.findOneByName(name);
    }

    @PutMapping("/actor")
    public void addActedInRelation(@RequestParam String name, @RequestParam String title) {
        personService.addActedInRelation(name, title);
    }

    @PutMapping("/director")
    public void addDirectedRelation(@RequestParam String name, @RequestParam String title) {
        personService.addDirectedRelation(name, title);
    }

    @DeleteMapping("/actor")
    public void deleteDirectedRelation(@RequestParam String name, @RequestParam String title) {
        personService.deleteDirectedRelation(name, title);
    }

    @DeleteMapping("/director")
    public void deleteActedInRelation(@RequestParam String name, @RequestParam String title) {
        personService.deleteActedInRelation(name, title);
    }

    @GetMapping("/actors")
    public Set<Person> getMovieActors(@RequestParam String title) {
        return personService.getMovieActors( title);
    }

    @GetMapping("/directors")
    public Set<Person> getMovieDirectors(@RequestParam String title) {
        return personService.getMovieDirectors(title);
    }

    @GetMapping("/coActors")
    public Set<Person> getCoActors(String name) {
        return personService.getCoActors(name);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable String id) {
        personService.deleteById(id);
        return new ResponseEntity<>("Movie successfully deleted.", HttpStatus.OK);
    }
}
