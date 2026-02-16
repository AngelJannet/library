package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Favorite;
import com.example.library.entity.FavoriteId;
import com.example.library.entity.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.FavoriteRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public FavoriteService(FavoriteRepository favoriteRepository,
                           UserRepository userRepository,
                           BookRepository bookRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void addToFavorites(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        FavoriteId favoriteId = new FavoriteId(userId, bookId);

        if (favoriteRepository.existsById(favoriteId)) {
            return;
        }

        Favorite favorite = new Favorite(favoriteId, user, book);
        favoriteRepository.save(favorite);
    }

    public void removeFromFavorites(Long userId, Long bookId) {
        FavoriteId favoriteId = new FavoriteId(userId, bookId);
        favoriteRepository.deleteById(favoriteId);
    }

    public List<Book> getUserFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId)
                .stream()
                .map(Favorite::getBook)
                .toList();
    }
}
