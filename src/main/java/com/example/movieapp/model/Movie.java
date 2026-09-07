package com.example.movieapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String title;

    @Column(nullable = false)
    @Min(1900)
    @Max(2026)
    private Integer year;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private GenreMovie genreMovie;

    @Min(0)
    @Max(10)
    private Double rating;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    @NotBlank
    private String posterUrl;

    @Column(nullable = false)
    @NotBlank
    private String director;

    private String actors;

    public Movie() {}

    public Movie(String title, Integer year, GenreMovie genreMovie,
                 Double rating, String description,
                 String posterUrl, String director, String actors) {
        this.title = title;
        this.year = year;
        this.genreMovie = genreMovie;
        this.rating = rating;
        this.description = description;
        this.posterUrl = posterUrl;
        this.director = director;
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
