package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestParam String title,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) Integer publishedYear,
                           @RequestParam Set<Long> authorIds,
                           @RequestParam Set<Integer> genreIds) {

        return bookService.createBook(title, description, publishedYear, authorIds, genreIds);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
}

