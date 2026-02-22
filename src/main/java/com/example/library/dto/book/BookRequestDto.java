package com.example.library.dto.book;

import java.util.Set;

public class BookRequestDto {

    private String title;
    private String description;
    private Integer publishedYear;
    private Set<Long> authorIds;
    private Set<Long> genreIds;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public Set<Long> getAuthorIds() {
        return authorIds;
    }

    public Set<Long> getGenreIds() {
        return genreIds;
    }
}

