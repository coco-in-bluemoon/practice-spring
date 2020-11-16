package com.example.rating.repository;

import com.example.rating.domain.GameRating;

import java.util.List;
import java.util.Optional;

public interface GameRatingRepository {
    void save(GameRating gameRating);
    Optional<GameRating> findById(long id);
    Optional<GameRating> findByGameNameAndUserName(String gameName, String userName);
    List<GameRating> findByGameName(String gameName);
    List<GameRating> findByUserName(String userName);
    List<GameRating> findAll();
}
