package com.example.rating.repository;

import com.example.rating.domain.GameRating;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryGameRatingRepository implements GameRatingRepository {

    private static Map<Long, GameRating> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(GameRating gameRating) {
        gameRating.setId(++sequence);
        long storedId = gameRating.getId();
        store.put(storedId, gameRating);
    }

    @Override
    public Optional<GameRating> findById(long id) {
        GameRating result = store.get(id);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<GameRating> findByGameNameAndUserName(String gameName, String userName) {
        return store.values().stream()
                .filter(gameRating -> gameRating.getGameName().equals(gameName))
                .filter(gameRating -> gameRating.getUserName().equals(userName))
                .findAny();
    }

    @Override
    public List<GameRating> findByGameName(String gameName) {
        return store.values().stream()
                .filter(gameRating -> gameRating.getGameName().equals(gameName))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameRating> findByUserName(String userName) {
        return store.values().stream()
                .filter(gameRating -> gameRating.getUserName().equals(userName))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameRating> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
