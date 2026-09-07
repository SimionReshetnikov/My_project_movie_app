package com.example.movieapp.mapper;

import com.example.movieapp.dto.request.MovieRequestDto;
import com.example.movieapp.dto.response.MovieResponseDto;
import com.example.movieapp.model.Movie;

public class MovieMapper {

    //Request -> Entity
    public static Movie toEntity(MovieRequestDto dto) {
        return new Movie(
                dto.getTitle(),
                dto.getYear(),
                dto.getGenreMovie(),
                dto.getRating(),
                dto.getDescription(),
                dto.getPosterUrl(),
                dto.getDirector(),
                dto.getActors()
        );
    }

    //Entity -> Response
    public static MovieResponseDto toResponse(Movie movie) {
        return new MovieResponseDto(movie);
    }

    //Update Entity from DTO
    public static void updateEntity(Movie movie, MovieRequestDto dto) {
        if (dto.getTitle() != null) {
            movie.setTitle(dto.getTitle());
        }

        if (dto.getYear() != null) {
            movie.setYear(dto.getYear());
        }

        if (dto.getGenreMovie() != null) {
            movie.setGenreMovie(dto.getGenreMovie());
        }

        if (dto.getRating() != null) {
            movie.setRating(dto.getRating());
        }

        if (dto.getDescription() != null) {
            movie.setDescription(dto.getDescription());
        }

        if (dto.getPosterUrl() != null) {
            movie.setPosterUrl(dto.getPosterUrl());
        }

        if (dto.getDirector() != null) {
            movie.setDirector(dto.getDirector());
        }

        if (dto.getActors() != null) {
            movie.setActors(dto.getActors());
        }
    }
}
