package com.example.movieapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "genre")
    private List<Movie> movie;

    public Genre() {}

    public Genre(Long id, String name, List<Movie> movie) {
        this.id = id;
        this.name = name;
        this.movie = movie;
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

    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }
}
