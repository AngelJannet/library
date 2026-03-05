package com.example.library.controller;

import com.example.library.dto.book.BookRequestDto;
import com.example.library.dto.book.BookResponseDto;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponseDto createBook(@Valid @RequestBody BookRequestDto request) {
        Book book = bookService.createBook(
                request.getTitle(),
                request.getDescription(),
                request.getPublishedYear(),
                request.getAuthorIds(),
                request.getGenreIds()
        );
        return bookService.toDto(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/{id}")
    public BookResponseDto getBook(@PathVariable Long id) {
        return bookService.getBookDtoById(id);
    }

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportBooksCsv() {
        String csv = bookService.exportBooksToCsv();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=books.csv")
                .contentType(MediaType.valueOf("text/csv"))
                .body(csv);
    }
}
