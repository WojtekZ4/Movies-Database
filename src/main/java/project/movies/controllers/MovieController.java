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
    public MovieController (MovieService movieService) {
        this.movieService = movieService;
    }

    @PutMapping
    public ResponseEntity<Movie> createOrUpdateMovie(@RequestBody Movie newMovie) {
        return new ResponseEntity<>(movieService.saveMovie(newMovie), HttpStatus.OK);
    }

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Collection<Movie>> getMovies() {
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-title")
    public ResponseEntity<Movie> byTitle(@RequestParam String title) {
        return new ResponseEntity<>(movieService.findOneByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/by-actor")
    public ResponseEntity<Set<Movie>> getActorsMovies(@RequestParam String name) {
        return new ResponseEntity<>(movieService.getActorsMovies(name), HttpStatus.OK);
    }

    @GetMapping("/by-director")
    public ResponseEntity<Set<Movie>> getDirectorsMovies(@RequestParam String name) {
        return new ResponseEntity<>(movieService.getDirectorsMovies(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable String id) {
        movieService.deleteById(id);
        return new ResponseEntity<>("Movie successfully deleted.", HttpStatus.OK);
    }
}
