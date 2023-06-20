package project.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.movies.models.Movie;
import project.movies.services.MovieService;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PutMapping
    public Movie createOrUpdateMovie(@RequestBody Movie newMovie) {
        return movieService.saveMovie(newMovie);
    }

    @GetMapping(value = { "", "/" })
    Collection<Movie> getMovies() {
        return movieService.findAll();
    }

    @GetMapping("/by-title")
    Movie byTitle(@RequestParam String title) {
        return movieService.findOneByTitle(title);
    }

    @GetMapping("/by-actor")
    public Set<Movie> getActorsMovies(@RequestParam String name) {
        return movieService.getActorsMovies(name);
    }

    @GetMapping("/by-director")
    public Set<Movie> getDirectorsMovies(@RequestParam String name) {
        return movieService.getDirectorsMovies(name);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable String id) {
        movieService.deleteById(id);
        return new ResponseEntity<>("Movie successfully deleted.", HttpStatus.OK);
    }
}
