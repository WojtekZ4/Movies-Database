package project.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.movies.models.Movie;
import project.movies.models.Person;
import project.movies.repositories.MovieRepository;
import project.movies.repositories.PersonRepository;

import java.util.Collection;
import java.util.Set;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Set<Person> findAll() {
        return personRepository.getAllPeople();
    }

    public Person findOneByName(String name) {
        return personRepository.findOneByName(name);
    }

    public void addActedInRelation(String name, String title) {
        personRepository.addActedInRelation(name, title);
    }

    public void addDirectedRelation(String name, String title) {
        personRepository.addDirectedRelation(name, title);
    }

    public void deleteDirectedRelation(String name, String title) {
        personRepository.deleteDirectedRelation(name, title);
    }

    public void deleteActedInRelation(String name, String title) {
        personRepository.deleteActedInRelation(name, title);
    }

    public Set<Person> getMovieActors(String title) {
        return personRepository.getMovieActors( title);
    }
    public Set<Person> getMovieDirectors(String title) {
        return personRepository.getMovieDirectors(title);
    }

    public Set<Person> getCoActors(String name) {
        return personRepository.getCoActors(name);
    }

    public void deleteById(String id) {
        personRepository.deleteById(id);
    }
}
