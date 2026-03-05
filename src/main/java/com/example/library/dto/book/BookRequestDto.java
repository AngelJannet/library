package com.example.library.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class BookRequestDto {

    @NotBlank
    private String title;

    private String description;
    private Integer publishedYear;

    @NotEmpty
    private Set<Long> authorIds;

    @NotEmpty
    private Set<Integer> genreIds;
}
