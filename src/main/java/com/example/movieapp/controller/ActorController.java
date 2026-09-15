package com.example.movieapp.controller;

import com.example.movieapp.dto.request.ActorRequestDto;
import com.example.movieapp.dto.response.ActorResponseDto;
import com.example.movieapp.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorResponseDto createActor(@Valid @RequestBody ActorRequestDto actorRequestDto) {
        return actorService.createActor(actorRequestDto);
    }

    @PutMapping("/update/{id}")
    public ActorResponseDto updateActor(@PathVariable Long id,
                                        @Valid @RequestBody ActorRequestDto actorRequestDto) {
        return actorService.updateActor(id, actorRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
    }

    @GetMapping("/get/{id}")
    public ActorResponseDto getById(@PathVariable Long id) {
        return actorService.getById(id);
    }

    @GetMapping
    public Set<ActorResponseDto> getAll() {
        return actorService.getAll();
    }
}
