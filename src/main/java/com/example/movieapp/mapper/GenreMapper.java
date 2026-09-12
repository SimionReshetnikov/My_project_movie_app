package com.example.movieapp.mapper;

import com.example.movieapp.dto.request.GenreRequestDto;
import com.example.movieapp.dto.response.GenreResponseDto;
import com.example.movieapp.model.Genre;

public class GenreMapper {

    public static Genre toEntity(GenreRequestDto genreRequestDto) {
        return new Genre(
                genreRequestDto.getName()
        );
    }

    public static GenreResponseDto toResponse(Genre genre) {
        return new GenreResponseDto(genre);
    }

    public static void updateEntity(Genre genre, GenreRequestDto genreRequestDto) {
        if (genreRequestDto.getName() != null) {
            genre.setName(genreRequestDto.getName());
        }
    }
}
