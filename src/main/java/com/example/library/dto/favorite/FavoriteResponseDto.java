package com.example.library.dto.favorite;

public class FavoriteResponseDto {

    private Long userId;
    private Long bookId;

    public FavoriteResponseDto(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }
}

