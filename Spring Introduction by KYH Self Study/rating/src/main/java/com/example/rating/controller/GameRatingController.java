package com.example.rating.controller;

import com.example.rating.domain.GameRating;
import com.example.rating.service.GameRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GameRatingController {

    private GameRatingService gameRatingService;

    @Autowired
    public GameRatingController(GameRatingService gameRatingService) {
        this.gameRatingService = gameRatingService;
    }

    @GetMapping("/gameRatings/new")
    public String createForm(Model model) {
        return "gameRating/createGameRating";
    }

    @PostMapping("/gameRatings/new")
    public String create(GameRatingForm form) {
        GameRating gameRating = new GameRating();
        gameRating.setGameName(form.getGameName());
        gameRating.setUserName(form.getUserName());
        gameRating.setRating(form.getRating());

        gameRatingService.join(gameRating);

        return "redirect:/";
    }

    @GetMapping("/gameRatings")
    public String list(Model model) {
        List<GameRating> ratings = gameRatingService.findRatings();
        model.addAttribute("ratings", ratings);
        return "gameRating/listGameRating";
    }

}
