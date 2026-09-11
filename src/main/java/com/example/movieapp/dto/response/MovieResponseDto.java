package com.example.movieapp.dto.response;

import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;

import java.util.Set;

public class MovieResponseDto {

    private Long id;
    private String title;
    private Integer year;
    private Set<Genre> genreMovie;
    private Double rating;
    private String description;
    private String posterUrl;
    private String director;
    private String actors;

    public MovieResponseDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.genreMovie = movie.getGenreMovie();
        this.rating = movie.getRating();
        this.description = movie.getDescription();
        this.posterUrl = movie.getPosterUrl();
        this.director = movie.getDirector();
        this.actors = movie.getActors();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public Set<Genre> getGenreMovie() {
        return genreMovie;
    }

    public Double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }
}
