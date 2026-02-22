package com.example.library.dto.book;

import lombok.Data;
import java.util.Set;

@Data
public class BookResponseDto {

    private Long id;
    private String title;
    private String description;
    private Integer publishedYear;
    private Set<String> authors;
    private Set<String> genres;
}
