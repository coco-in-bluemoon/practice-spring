package com.example.rating;

import com.example.rating.repository.GameRatingRepository;
import com.example.rating.repository.MemoryGameRatingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public GameRatingRepository gameRatingRepository() {
        return new MemoryGameRatingRepository();
    }
}
