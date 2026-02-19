package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public void addFavorite(@RequestParam Long userId,
                            @RequestParam Long bookId) {

        favoriteService.addToFavorites(userId, bookId);
    }

    @GetMapping("/{userId}")
    public List<Book> getUserFavorites(@PathVariable Long userId) {
        return favoriteService.getUserFavorites(userId);
    }
}

