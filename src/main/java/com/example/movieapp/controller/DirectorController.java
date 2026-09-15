package com.example.movieapp.controller;

import com.example.movieapp.dto.request.DirectorRequestDto;
import com.example.movieapp.dto.response.DirectorResponseDto;
import com.example.movieapp.service.DirectorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DirectorResponseDto createDirector(@Valid @RequestBody DirectorRequestDto directorRequestDto) {
        return directorService.createDirector(directorRequestDto);
    }

    @PutMapping("/update/{id}")
    public DirectorResponseDto updateDirector(@PathVariable Long id,
                                              @Valid @RequestBody DirectorRequestDto dtoUpdate) {
        return directorService.updateDirector(id, dtoUpdate);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
    }

    @GetMapping("/get/{id}")
    public DirectorResponseDto getById(@PathVariable Long id) {
        return directorService.getById(id);
    }

    @GetMapping
    public Set<DirectorResponseDto> getAll() {
        return directorService.getAll();
    }
}
