package com.example.movieapp.service;

import com.example.movieapp.dto.request.GenreRequestDto;
import com.example.movieapp.dto.response.GenreResponseDto;
import com.example.movieapp.mapper.GenreMapper;
import com.example.movieapp.model.Genre;
import com.example.movieapp.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public GenreResponseDto createGenre(GenreRequestDto dto) {
        Genre genre = GenreMapper.toEntity(dto);
        return GenreMapper.toResponse(genreRepository.save(genre));
    }

    public Set<GenreResponseDto> getAll() {
        return genreRepository.findAll().stream()
                .map(GenreMapper::toResponse).collect(Collectors.toSet());
    }

    public GenreResponseDto getById(Long id) {
        return GenreMapper.toResponse(genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No genre with this ID = %d was found.", id))));
    }

    public void deleteGenre(Long id) {
        genreRepository.delete(genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre is not found.")));
    }

    @Transactional
    public GenreResponseDto updateGenre(Long id, GenreRequestDto dtoUpdate) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre is not found."));
        GenreMapper.updateEntity(genre, dtoUpdate);
        return GenreMapper.toResponse(genreRepository.save(genre));
    }
}
