package com.example.movieapp.repository;

import com.example.movieapp.model.GenreMovie;
import com.example.movieapp.model.Movie;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByGenreMovie(GenreMovie genreMovie);
    List<Movie> findByYear(Integer year);
    List<Movie> findByGenreMovieAndYear(GenreMovie genre, Integer year);
    Page<Movie> findAll(Pageable pageable);
}
