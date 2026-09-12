package com.example.movieapp.mapper;

import com.example.movieapp.dto.request.DirectorRequestDto;
import com.example.movieapp.dto.response.DirectorResponseDto;
import com.example.movieapp.model.Director;

public class DirectorMapper {

    public static Director toEntity(DirectorRequestDto dto) {
        return new Director(
                dto.getName(),
                dto.getBirthYear()
        );
    }

    public static DirectorResponseDto toResponse(Director director) {
        return new DirectorResponseDto(director);
    }

    public static void updateEntity(Director director, DirectorRequestDto dto) {
        if (dto.getName() != null) {
            director.setName(dto.getName());
        }
        if (dto.getBirthYear() != null) {
            director.setBirthYear(dto.getBirthYear());
        }
    }
}
