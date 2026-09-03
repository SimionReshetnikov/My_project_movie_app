package com.example.movieapp.dto.response;

import com.example.movieapp.model.GenreMovie;
import com.example.movieapp.model.Movie;

public class MovieResponseDto {

    private Long id;
    private String title;
    private Integer year;
    private GenreMovie genreMovie;
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

    public GenreMovie getGenreMovie() {
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
