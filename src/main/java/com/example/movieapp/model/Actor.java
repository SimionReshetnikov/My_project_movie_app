package com.example.movieapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String name;

    @Min(1800)
    @Max(2026)
    private Integer birthYear;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> films = new HashSet<>();

    public Actor() {}

    public Actor(String name, Integer yearBirth) {
        this.name = name;
        this.birthYear = yearBirth;
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

    public Integer getYearBirth() {
        return birthYear;
    }

    public void setYearBirth(Integer yearBirth) {
        this.birthYear = yearBirth;
    }

    public Set<Movie> getFilms() {
        return films;
    }

    public void setFilms(Set<Movie> films) {
        this.films = films;
    }
}
