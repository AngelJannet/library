package com.example.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    protected Role() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
