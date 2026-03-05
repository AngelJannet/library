package com.example.library.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "published_year")
    private Integer publishedYear;

    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;

    protected Book() {
    }

    public Book(String title, Integer publishedYear) {
        this.title = title;
        this.publishedYear = publishedYear;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
