package com.example.movieapp.service;

import com.example.movieapp.dto.request.ActorRequestDto;
import com.example.movieapp.dto.response.ActorResponseDto;
import com.example.movieapp.mapper.ActorMapper;
import com.example.movieapp.model.Actor;
import com.example.movieapp.repository.ActorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public ActorResponseDto createActor(ActorRequestDto dto) {
        Actor actor = ActorMapper.toEntity(dto);
        return ActorMapper.toResponse(actorRepository.save(actor));
    }

    @Transactional
    public ActorResponseDto updateActor(Long id, ActorRequestDto updateDto) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor is not found"));
        ActorMapper.updateEntity(actor, updateDto);
        return ActorMapper.toResponse(actorRepository.save(actor));
    }

    public ActorResponseDto getById(Long id) {
        return ActorMapper.toResponse(actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor is not found")));
    }

    public Set<ActorResponseDto> getAll() {
        return actorRepository.findAll().stream().map(ActorMapper::toResponse)
                .collect(Collectors.toSet());
    }

    public void deleteActor(Long id) {
        actorRepository.delete(actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor is not found")));
    }
}
