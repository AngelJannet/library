package com.example.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @EmbeddedId
    private FavoriteId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    protected Favorite() {
    }

    public Favorite(FavoriteId id, User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }

    public FavoriteId getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

}