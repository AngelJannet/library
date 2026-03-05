package com.example.library.controller;

import com.example.library.dto.book.BookResponseDto;
import com.example.library.entity.User;
import com.example.library.service.BookService;
import com.example.library.service.FavoriteService;
import com.example.library.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserService userService;
    private final BookService bookService;

    public FavoriteController(FavoriteService favoriteService,
                              UserService userService,
                              BookService bookService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping
    public void addFavorite(@RequestParam Long bookId, Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        favoriteService.addToFavorites(currentUser.getId(), bookId);
    }

    @DeleteMapping("/{bookId}")
    public void removeFavorite(@PathVariable Long bookId, Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        favoriteService.removeFromFavorites(currentUser.getId(), bookId);
    }

    @GetMapping
    public List<BookResponseDto> getCurrentUserFavorites(Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        return favoriteService.getUserFavorites(currentUser.getId())
                .stream()
                .map(bookService::toDto)
                .toList();
    }
}
