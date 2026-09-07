package com.example.movieapp.service;

import com.example.movieapp.dto.request.MovieRequestDto;
import com.example.movieapp.dto.response.MovieResponseDto;
import com.example.movieapp.mapper.MovieMapper;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.GenreMovie;
import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponseDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::toResponse).toList();
    }

    public MovieResponseDto getMovieById(Long id) {
         return MovieMapper.toResponse(movieRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException(String.format("No film with this ID = %d was found.", id))));
    }

    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto) {
        Movie movie = MovieMapper.toEntity(movieRequestDto);
        return MovieMapper.toResponse(movieRepository.save(movie));
    }

    public MovieResponseDto updateMovie(Long id, MovieRequestDto movieDetails) {
        Movie movieSave = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No film with this ID = %d was found.", id)));
        MovieMapper.updateEntity(movieSave, movieDetails);
        return MovieMapper.toResponse(movieRepository.save(movieSave));
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No film with this ID = %d was found.", id)));
        movieRepository.delete(movie);
    }

    public List<MovieResponseDto> searchByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("A zero or empty value has been passed for the title field.");
        }

        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(MovieMapper::toResponse).toList();
    }

    public List<MovieResponseDto> filterMovies(Genre genre, Integer year) {
        if (genre != null && year != null) {
            return movieRepository.findByGenreMovieAndYear(genre, year).stream()
                    .map(MovieMapper::toResponse).toList();
        } else if (genre != null) {
            return movieRepository.findByGenreMovie(genre).stream()
                    .map(MovieMapper::toResponse).toList();
        } else if (year != null) {
            return movieRepository.findByYear(year).stream()
                    .map(MovieMapper::toResponse).toList();
        }

        return getAllMovies();
    }

    public Page<MovieResponseDto> getMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.map(MovieMapper::toResponse);
    }

    @Transactional
    public MovieResponseDto updatePosterUrlMovie(Long id, MultipartFile posterUrlFile) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No film with this ID = %d was found.", id)));

        if(posterUrlFile.isEmpty()) {
            throw new IllegalArgumentException("File posterUrlFile is empty.");
        }

        String uploadDir = "uploads/posters";
        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = "movie-" + id + ".jpg";
            Path path = Paths.get(uploadDir + fileName);
            posterUrlFile.transferTo(path.toFile());

            movie.setPosterUrl("/" + uploadDir + fileName);

            Movie saveMovie = movieRepository.save(movie);

            return MovieMapper.toResponse(saveMovie);
        } catch(IOException ex) {
            throw new RuntimeException("Failed to save poster", ex);
        }
    }
}
