package com.example.movieapp.service;

import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No film with this ID = %d was found.", id)));
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movieSave = getMovieById(id);

        movieSave.setTitle(movieDetails.getTitle());
        movieSave.setYear(movieDetails.getYear());
        movieSave.setGenreMovie(movieDetails.getGenreMovie());
        movieSave.setRating(movieDetails.getRating());
        movieSave.setDescription(movieDetails.getDescription());
        movieSave.setPosterUrl(movieDetails.getPosterUrl());
        movieSave.setDirector(movieDetails.getDirector());
        movieSave.setActors(movieDetails.getActors());

        return movieRepository.save(movieSave);
    }

    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }
}
