package com.example.movieapp.dto.response;

import com.example.movieapp.model.Movie;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieResponseDto {

    private Long id;
    private String title;
    private Integer year;
    private Set<GenreResponseDto> genreMovies;
    private Double rating;
    private String description;
    private String posterUrl;
    private DirectorResponseDto director;
    private Set<ActorResponseDto> actors;

    public MovieResponseDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.rating = movie.getRating();
        this.description = movie.getDescription();
        this.posterUrl = movie.getPosterUrl();
        this.director = movie.getDirector() != null ?
                new DirectorResponseDto(movie.getDirector()) : null;
        this.genreMovies = movie.getGenreMovie().stream()
                .map(GenreResponseDto::new).collect(Collectors.toSet());
        this.actors = movie.getActors().stream()
                .map(ActorResponseDto::new).collect(Collectors.toSet());
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

    public Set<GenreResponseDto> getGenreMovies() {
        return genreMovies;
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

    public DirectorResponseDto getDirector() {
        return director;
    }

    public Set<ActorResponseDto> getActors() {
        return actors;
    }
}
