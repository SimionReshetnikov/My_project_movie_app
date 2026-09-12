package com.example.movieapp.dto.response;

import com.example.movieapp.model.Actor;

public class ActorResponseDto {

    private Long id;
    private String name;
    private Integer birthYear;

    public ActorResponseDto(Actor actor) {
        this.id = actor.getId();
        this.name = actor.getName();
        this.birthYear = actor.getYearBirth();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }
}
