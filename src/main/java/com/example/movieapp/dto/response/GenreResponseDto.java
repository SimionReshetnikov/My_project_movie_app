package com.example.movieapp.dto.response;

import com.example.movieapp.model.Genre;

public class GenreResponseDto {

    private Long id;
    private String name;

    public GenreResponseDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
