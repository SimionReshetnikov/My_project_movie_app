package com.example.movieapp.dto.response;

import com.example.movieapp.model.Director;

public class DirectorResponseDto {

    private Long id;
    private String name;
    private Integer birthYear;

    private DirectorResponseDto(Director director) {
        this.id = director.getId();
        this.name = director.getName();
        this.birthYear = director.getBirthYear();
    }

    public Long getId() {
        return id;
    }

    private String getName() {
        return name;
    }

    private Integer getBirthYear() {
        return birthYear;
    }
}
