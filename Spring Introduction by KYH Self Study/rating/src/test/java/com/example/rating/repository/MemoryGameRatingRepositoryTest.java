package com.example.rating.repository;

import com.example.rating.domain.GameRating;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryGameRatingRepositoryTest {
    MemoryGameRatingRepository gameRatingRepository = new MemoryGameRatingRepository();

    @AfterEach
    public void afterEach() {
        gameRatingRepository.clearStore();
    }

    @Test
    public void save() {
        GameRating gameRating = new GameRating();
        gameRating.setGameName("game1");
        gameRating.setUserName("user1");
        gameRating.setRating(5.0);
        gameRatingRepository.save(gameRating);

        long savedId = gameRating.getId();
        GameRating result = gameRatingRepository.findById(savedId).get();

        assertThat(result).isEqualTo(gameRating);
    }

    @Test
    public void findByGameNameAndUserName() {
        GameRating gameRating1 = new GameRating();
        gameRating1.setGameName("game1");
        gameRating1.setUserName("user1");
        gameRating1.setRating(5.0);
        gameRatingRepository.save(gameRating1);

        GameRating gameRating2 = new GameRating();
        gameRating2.setGameName("game2");
        gameRating2.setUserName("user2");
        gameRating2.setRating(5.0);
        gameRatingRepository.save(gameRating2);

        GameRating result = gameRatingRepository.findByGameNameAndUserName("game1", "user1").get();

        assertThat(result).isEqualTo(gameRating1);
    }

    @Test
    public void findByGameName() {
        GameRating gameRating1 = new GameRating();
        gameRating1.setGameName("game1");
        gameRating1.setUserName("user1");
        gameRating1.setRating(5.0);
        gameRatingRepository.save(gameRating1);

        GameRating gameRating2 = new GameRating();
        gameRating2.setGameName("game1");
        gameRating2.setUserName("user2");
        gameRating2.setRating(5.0);
        gameRatingRepository.save(gameRating2);

        List<GameRating> result = gameRatingRepository.findByGameName("game1");

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findByUserName() {
        GameRating gameRating1 = new GameRating();
        gameRating1.setGameName("game1");
        gameRating1.setUserName("user1");
        gameRating1.setRating(5.0);
        gameRatingRepository.save(gameRating1);

        GameRating gameRating2 = new GameRating();
        gameRating2.setGameName("game2");
        gameRating2.setUserName("user1");
        gameRating2.setRating(5.0);
        gameRatingRepository.save(gameRating2);

        List<GameRating> result = gameRatingRepository.findByUserName("user1");

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findAll() {
        GameRating gameRating1 = new GameRating();
        gameRating1.setGameName("game1");
        gameRating1.setUserName("user1");
        gameRating1.setRating(5.0);
        gameRatingRepository.save(gameRating1);

        GameRating gameRating2 = new GameRating();
        gameRating2.setGameName("game2");
        gameRating2.setUserName("user2");
        gameRating2.setRating(5.0);
        gameRatingRepository.save(gameRating2);

        List<GameRating> result = gameRatingRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
