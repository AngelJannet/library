package com.example.library.service;

import com.example.library.entity.Genre;
import com.example.library.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre getOrCreateGenre(String title){
        return genreRepository.findByTitle(title)
                .orElseGet(() -> genreRepository.save(new Genre(title)));
    }

    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Genre not found with id: " + id));
    }
}
