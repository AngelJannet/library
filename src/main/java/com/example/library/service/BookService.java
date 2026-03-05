package com.example.library.service;

import com.example.library.dto.book.BookResponseDto;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Genre;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<BookResponseDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Book createBook(String title,
                           String description,
                           Integer publishedYear,
                           Set<Long> authorIds,
                           Set<Integer> genreIds) {

        Set<Author> authors = authorRepository.findAllById(authorIds)
                .stream()
                .collect(Collectors.toSet());

        if (authors.size() != authorIds.size()) {
            throw new RuntimeException("One or more authors not found");
        }

        Set<Genre> genres = genreRepository.findAllById(genreIds)
                .stream()
                .collect(Collectors.toSet());

        if (genres.size() != genreIds.size()) {
            throw new RuntimeException("One or more genres not found");
        }

        Book book = new Book(title, publishedYear);
        book.setDescription(description);
        book.setAuthors(authors);
        book.setGenres(genres);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public String exportBooksToCsv() {
        StringBuilder csv = new StringBuilder("id,title,description,publishedYear\n");
        for (Book book : bookRepository.findAll()) {
            csv.append(book.getId()).append(',')
                    .append(escapeCsv(book.getTitle())).append(',')
                    .append(escapeCsv(book.getDescription())).append(',')
                    .append(book.getPublishedYear() == null ? "" : book.getPublishedYear())
                    .append('\n');
        }
        return csv.toString();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with id: " + id));
    }

    public BookResponseDto getBookDtoById(Long id) {
        return toDto(getBookById(id));
    }

    public BookResponseDto toDto(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setPublishedYear(book.getPublishedYear());

        if (book.getAuthors() != null) {
            dto.setAuthors(book.getAuthors().stream()
                    .map(Author::getName)
                    .collect(Collectors.toSet()));
        }

        if (book.getGenres() != null) {
            dto.setGenres(book.getGenres().stream()
                    .map(Genre::getTitle)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }
        String escaped = value.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}
