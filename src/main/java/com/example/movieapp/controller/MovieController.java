package com.example.movieapp.controller;

import com.example.movieapp.dto.request.MovieRequestDto;
import com.example.movieapp.dto.response.MovieResponseDto;
import com.example.movieapp.model.Genre;
import com.example.movieapp.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieResponseDto getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public MovieResponseDto createMovie(@Valid @RequestBody MovieRequestDto movie) {
        return movieService.createMovie(movie);
    }

    @PostMapping("/{id}/poster")
    public MovieResponseDto uploadPoster(@PathVariable Long id,
                                         @RequestParam("file")MultipartFile file) {
        return movieService.updatePosterUrlMovie(id, file);
    }

    @PutMapping("/{id}")
    public MovieResponseDto updateMovie(@PathVariable Long id,
                                        @Valid @RequestBody MovieRequestDto movie) {
        return movieService.updateMovie(id ,movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/search")
    public List<MovieResponseDto> searchByTitle(@RequestParam String title) {
        return movieService.searchByTitle(title);
    }

    @GetMapping("/filter")
    public List<MovieResponseDto> filterMovies(
            @RequestParam(required = false) Genre genreMovie,
            @RequestParam(required = false) Integer year
            ) {
        return movieService.filterMovies(genreMovie, year);
    }


    @GetMapping("/page")
    public Page<MovieResponseDto> getMoviesPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
            ) {
        return movieService.getMovies(page, size);
    }
}
