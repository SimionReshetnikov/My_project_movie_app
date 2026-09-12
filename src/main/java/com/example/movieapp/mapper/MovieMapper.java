package com.example.movieapp.mapper;

import com.example.movieapp.dto.request.MovieRequestDto;
import com.example.movieapp.dto.response.MovieResponseDto;
import com.example.movieapp.model.Actor;
import com.example.movieapp.model.Director;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;

import java.util.Set;

public class MovieMapper {

    //Request -> Entity
    public static Movie toEntity(MovieRequestDto dto, Director director) {
        return new Movie(
                dto.getTitle(),
                dto.getYear(),
                dto.getRating(),
                dto.getDescription(),
                dto.getPosterUrl(),
                director
        );
    }

    //Entity -> Response
    public static MovieResponseDto toResponse(Movie movie) {
        return new MovieResponseDto(movie);
    }

    //Update Entity from DTO
    public static void updateEntity(Movie movie, MovieRequestDto dto,
                                    Set<Genre> genres, Director director,
                                    Set<Actor> actors) {
        if (dto.getTitle() != null) {
            movie.setTitle(dto.getTitle());
        }

        if (dto.getYear() != null) {
            movie.setYear(dto.getYear());
        }

        if (dto.getGenreMovie() != null) {
            movie.setGenreMovie(genres);
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
            movie.setDirector(director);
        }

        if (dto.getActorIds() != null) {
            movie.setActors(actors);
        }
    }
}
