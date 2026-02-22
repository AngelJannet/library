package com.example.library.dto.genre;

import lombok.Data;

import java.util.Set;

@Data
public class GenreResponseDto {
    private String title;
    private String description;
    private Integer publishedYear;
    private Set<Long> authorIds;
    private Set<Long> genreIds;
}
