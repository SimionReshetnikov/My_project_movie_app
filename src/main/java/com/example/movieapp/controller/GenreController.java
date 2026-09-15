package com.example.movieapp.controller;

import com.example.movieapp.dto.request.GenreRequestDto;
import com.example.movieapp.dto.response.GenreResponseDto;
import com.example.movieapp.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreResponseDto createGenre(@Valid @RequestBody GenreRequestDto genreRequestDto) {
        return genreService.createGenre(genreRequestDto);
    }

    @PutMapping("/update/{id}")
    public GenreResponseDto updateGenre(@PathVariable Long id,
                                        @Valid @RequestBody GenreRequestDto updateDto) {
        return genreService.updateGenre(id, updateDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }

    @GetMapping("/get/{id}")
    public GenreResponseDto getById(@PathVariable Long id) {
        return genreService.getById(id);
    }

    @GetMapping
    public Set<GenreResponseDto> getAll() {
        return genreService.getAll();
    }

}
