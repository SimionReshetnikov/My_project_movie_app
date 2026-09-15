package com.example.movieapp.service;

import com.example.movieapp.dto.request.DirectorRequestDto;
import com.example.movieapp.dto.response.DirectorResponseDto;
import com.example.movieapp.mapper.DirectorMapper;
import com.example.movieapp.model.Director;
import com.example.movieapp.repository.DirectorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public DirectorResponseDto createDirector(DirectorRequestDto dto) {
        Director director = DirectorMapper.toEntity(dto);
        return DirectorMapper.toResponse(directorRepository.save(director));
    }

    public DirectorResponseDto updateDirector(Long id, DirectorRequestDto dtoUpdate) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Director is not found"));

        DirectorMapper.updateEntity(director, dtoUpdate);
        return DirectorMapper.toResponse(directorRepository.save(director));
    }

    public DirectorResponseDto getById(Long id) {
        return DirectorMapper.toResponse(directorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Director is not found")));
    }

    public Set<DirectorResponseDto> getAll() {
        return directorRepository.findAll().stream().map(DirectorMapper::toResponse)
                .collect(Collectors.toSet());
    }

    public void deleteDirector(Long id) {
        directorRepository.delete(directorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No director with this ID = %d was found.", id))));
    }
}
