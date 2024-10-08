package com.example.demo.movies.controllers;


import com.example.demo.movies.entities.Movie;
import com.example.demo.movies.entities.MovieRequest;
import com.example.demo.movies.repositories.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MoviesController {

    @Autowired
    private MoviesRepository moviesRepository;


    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        final List<Movie> movies= moviesRepository.findAll();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        final Movie movie = moviesRepository.findById(id).orElse(null);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequest movie) {
        final Movie savedMovie = moviesRepository.save(new Movie(null, movie.getTitle(), movie.getGenre(), movie.getDirector(), movie.getReleaseDate()));
        return ResponseEntity.ok(savedMovie);
    }

    @PutMapping("/")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        final Movie updatedMovie = moviesRepository.save(movie);
        return ResponseEntity.ok(updatedMovie);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        moviesRepository.deleteById(id);
        return ResponseEntity.ok("Movie deleted");
    }


}
