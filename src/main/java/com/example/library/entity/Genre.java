package com.example.library.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    protected Genre() {
    }

    public Genre(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

}
