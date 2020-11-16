package com.example.rating.service;

import com.example.rating.domain.GameRating;
import com.example.rating.repository.GameRatingRepository;
import com.example.rating.repository.MemoryGameRatingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GameRatingServiceTest {
    private GameRatingService gameRatingService;
    private MemoryGameRatingRepository gameRatingRepository;

    @BeforeEach
    public void beforeEach() {
        gameRatingRepository = new MemoryGameRatingRepository();
        gameRatingService = new GameRatingService(gameRatingRepository);
    }

    @AfterEach
    public void afterEach() {
        gameRatingRepository.clearStore();
    }

    @Test
    public void join() {
        GameRating gameRating = new GameRating();
        gameRating.setGameName("game1");
        gameRating.setUserName("user1");
        gameRating.setRating(5.0);
        GameRating result = gameRatingService.join(gameRating);

        assertThat(result.getUserName()).isEqualTo(gameRating.getUserName());
        assertThat(result.getGameName()).isEqualTo(gameRating.getGameName());
        assertThat(result.getRating()).isEqualTo(gameRating.getRating());
    }

    @Test
    public void validateDuplicatedRating() {
        GameRating gameRating1 = new GameRating();
        gameRating1.setGameName("game1");
        gameRating1.setUserName("user1");
        gameRating1.setRating(5.0);

        GameRating gameRating2 = new GameRating();
        gameRating2.setGameName("game1");
        gameRating2.setUserName("user1");
        gameRating2.setRating(5.0);

        gameRatingService.join(gameRating1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> gameRatingService.join(gameRating2));
        assertThat(e.getMessage()).isEqualTo("사용자가 이미 평가한 게임 정보입니다.");
    }
}
