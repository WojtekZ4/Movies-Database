package project.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.movies.models.Movie;
import project.movies.models.Person;
import project.movies.repositories.MovieRepository;

import java.util.Collection;
import java.util.Set;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final PersonService personService;
    @Autowired
    public MovieService(MovieRepository movieRepository, PersonService personService) {
        this.movieRepository = movieRepository;
        this.personService = personService;
    }

    public Movie saveMovie(Movie movie) {
        var out = movieRepository.save(movie);

        for (Person a :movie.getActors())
            personService.addActedInRelation(a.getName(), movie.getTitle());

        for (Person d :movie.getDirectors())
            personService.addDirectedRelation(d.getName(), movie.getTitle());

        packMovie(out);
        return out;
    }

    public Collection<Movie> findAll() {
        Set<Movie> out = movieRepository.getAllMovies();
        out.forEach(this::packMovie);
        return out;
    }

    public Set<Movie> getActorsMovies(String name) {
        Set<Movie> out = movieRepository.getActorsMovies(name);
        out.forEach(this::packMovie);
        return out;
    }

    public Set<Movie> getDirectorsMovies(String name) {
        Set<Movie> out = movieRepository.getDirectorsMovies(name);
        out.forEach(this::packMovie);
        return out;
    }
    public Movie findOneByTitle(String title) {
        Movie out = movieRepository.findOneByTitle(title);
        packMovie(out);
        return out;
    }

    private void packMovie(Movie movie){
        movie.setActors(personService.getMovieActors(movie.getTitle()));
        movie.setDirectors(personService.getMovieDirectors(movie.getTitle()));
    }

    public void deleteById(String id) {
//        personService.getMovieDirectors(id).forEach(director -> personService.deleteDirectedRelation(director.getName(),id));
//        personService.getMovieActors(id).forEach(actors -> personService.deleteActedInRelation(actors.getName(),id));
        movieRepository.deleteById(id);
    }
}
