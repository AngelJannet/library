package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Genre;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public Book createBook(String title,
                           String description,
                           Integer publishedYear,
                           Set<Long> authorIds,
                           Set<Integer> genreIds) {

        Set<Author> authors = authorRepository.findAllById(authorIds)
                .stream()
                .collect(java.util.stream.Collectors.toSet());

        if (authors.size() != authorIds.size()) {
            throw new RuntimeException("One or more authors not found");
        }

        Set<Genre> genres = genreRepository.findAllById(genreIds)
                .stream()
                .collect(java.util.stream.Collectors.toSet());

        if (genres.size() != genreIds.size()) {
            throw new RuntimeException("One or more genres not found");
        }

        Book book = new Book(title, publishedYear);
        book.setDescription(description);
        book.setAuthors(authors);
        book.setGenres(genres);

        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with id: " + id));
    }
}