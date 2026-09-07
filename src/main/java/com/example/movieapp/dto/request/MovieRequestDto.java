package com.example.movieapp.dto.request;

import com.example.movieapp.model.GenreMovie;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieRequestDto {

    @NotBlank
    private String title;

    @NotNull
    @Min(1900)
    @Max(2026)
    private Integer year;

    private GenreMovie genreMovie;

    @Min(0)
    @Max(10)
    private Double rating;

    private String description;

    private String posterUrl;

    @NotBlank
    private String director;

    @NotBlank
    private String actors;

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

    public GenreMovie getGenreMovie() {
        return genreMovie;
    }

    public void setGenreMovie(GenreMovie genreMovie) {
        this.genreMovie = genreMovie;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}
