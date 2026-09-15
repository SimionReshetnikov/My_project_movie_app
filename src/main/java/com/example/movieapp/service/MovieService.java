package com.example.movieapp.service;

import com.example.movieapp.dto.request.MovieRequestDto;
import com.example.movieapp.dto.response.MovieResponseDto;
import com.example.movieapp.exception.FileStorageException;
import com.example.movieapp.mapper.MovieMapper;
import com.example.movieapp.model.Actor;
import com.example.movieapp.model.Director;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.ActorRepository;
import com.example.movieapp.repository.DirectorRepository;
import com.example.movieapp.repository.GenreRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    public MovieService(MovieRepository movieRepository, DirectorRepository directorRepository,
                        GenreRepository genreRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
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
        Director director = findDirector(movieRequestDto.getDirector());
        Set<Genre> genres = findGenres(movieRequestDto.getGenreMovie());
        Set<Actor> actors = findActors(movieRequestDto.getActorIds());
        Movie movie = MovieMapper.toEntity(movieRequestDto, director, genres, actors);
        return MovieMapper.toResponse(movieRepository.save(movie));
    }

    @Transactional
    public MovieResponseDto updateMovie(Long id, MovieRequestDto movieDetails) {
        Movie movieSave = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No film with this ID = %d was found.", id)));

        Director director = findDirector(movieDetails.getDirector());
        Set<Genre> genres = findGenres(movieDetails.getGenreMovie());
        Set<Actor> actors = findActors(movieDetails.getActorIds());

        MovieMapper.updateEntity(movieSave, movieDetails, genres, director, actors);
        return MovieMapper.toResponse(movieRepository.save(movieSave));
    }

    public void deleteMovie(Long id) {
        movieRepository.delete(movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No film with this ID = %d was found.", id))));
    }

    public List<MovieResponseDto> searchByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("A zero or empty value has been passed for the title field.");
        }

        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(MovieMapper::toResponse).toList();
    }

    public List<MovieResponseDto> filterMovies(Long genreId, Integer year) {
        if (genreId != null && year != null) {
            return movieRepository.findByGenreMovieAndYear(findGenre(genreId), year).stream()
                    .map(MovieMapper::toResponse).toList();
        } else if (genreId != null) {
            return movieRepository.findByGenreMovie(findGenre(genreId)).stream()
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
            Path path = Paths.get(uploadDir, fileName);
            posterUrlFile.transferTo(path.toFile());

            movie.setPosterUrl("/" + uploadDir + "/" + fileName);

            Movie saveMovie = movieRepository.save(movie);

            return MovieMapper.toResponse(saveMovie);
        } catch(IOException ex) {
            throw new FileStorageException("Failed to save poster", ex);
        }
    }

    private Director findDirector(Long directorId) {
        return directorRepository.findById(directorId)
                .orElseThrow(() -> new EntityNotFoundException("Director is not found."));
    }

    private Genre findGenre(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre is not found."));
    }

    private Set<Genre> findGenres(Set<Long> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(genreRepository.findAllById(genreIds));
    }

    private Set<Actor> findActors(Set<Long> actorsId) {
        if (actorsId == null || actorsId.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(actorRepository.findAllById(actorsId));
    }
}
