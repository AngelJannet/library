package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getOrCreateAuthor(String name) {
        return authorRepository.findByName(name)
                .orElseGet(() -> authorRepository.save(new Author(name)));
    }

    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Author not found with id: " + id));
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
