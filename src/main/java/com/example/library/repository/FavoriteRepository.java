package com.example.library.repository;

import com.example.library.entity.Favorite;
import com.example.library.entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {

    List<Favorite> findByUserId(Long UserId);
}
