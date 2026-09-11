package com.example.movieapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String name;

    @Min(1800)
    @Max(2026)
    private Integer birthYear;

    @OneToMany(mappedBy = "director", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Movie> films = new ArrayList<>();

    public Director() {}

    public Director(String name, Integer birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public void addMovie(Movie movie) {
        films.add(movie);
        movie.setDirector(this);
    }

    public void removeMovie(Movie movie) {
        films.remove(movie);
        movie.setDirector(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public List<Movie> getFilms() {
        return films;
    }

    public void setFilms(List<Movie> films) {
        this.films = films;
    }
}
