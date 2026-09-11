package com.example.movieapp.dto.request;

import com.example.movieapp.model.Actor;
import com.example.movieapp.model.Director;
import com.example.movieapp.model.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class MovieRequestDto {

    @NotBlank
    private String title;

    @NotNull
    @Min(1900)
    @Max(2026)
    private Integer year;

    private Set<Genre> genre;

    @Min(0)
    @Max(10)
    private Double rating;

    private String description;

    private String posterUrl;

    @NotBlank
    private Director director;

    @NotBlank
    private Set<Actor> actors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Genre> getGenreMovie() {
        return genre;
    }

    public void setGenreMovie(Set<Genre> genreMovie) {
        this.genre = genreMovie;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
