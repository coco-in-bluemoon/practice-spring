package com.example.rating.service;

import com.example.rating.domain.GameRating;
import com.example.rating.repository.GameRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRatingService {
    private GameRatingRepository gameRatingRepository;

    @Autowired
    public GameRatingService(GameRatingRepository gameRatingRepository) {
        this.gameRatingRepository = gameRatingRepository;
    }

    public GameRating join(GameRating gameRating) {
        validateDuplicatedRating(gameRating);
        gameRatingRepository.save(gameRating);
        return gameRating;
    }

    private void validateDuplicatedRating(GameRating gameRating) {
        gameRatingRepository.findByGameNameAndUserName(gameRating.getGameName(), gameRating.getUserName())
                .ifPresent(rating -> {
                    throw new IllegalStateException("사용자가 이미 평가한 게임 정보입니다.");
                });
    }

    public List<GameRating> findRatings() {
        return gameRatingRepository.findAll();
    }
}
