package com.example.movieapp.mapper;

import com.example.movieapp.dto.request.ActorRequestDto;
import com.example.movieapp.dto.response.ActorResponseDto;
import com.example.movieapp.model.Actor;

public class ActorMapper {

    public static Actor toEntity(ActorRequestDto dto) {
        return new Actor(
                dto.getName(),
                dto.getBirthYear()
        );
    }

    public static ActorResponseDto toResponse(Actor actor) {
        return new ActorResponseDto(actor);
    }

    public static void updateEntity(Actor actor, ActorRequestDto dto) {
        if (dto.getName() != null) {
            actor.setName(dto.getName());
        }
        if (dto.getBirthYear() != null) {
            actor.setYearBirth(dto.getBirthYear());
        }
    }
}
