/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Controller;

import com.sg.guesgame.Model.Game;
import com.sg.guesgame.Model.Round;
import com.sg.guesgame.Service.gameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author siyaa
 */
@RestController
@RequestMapping("/api/game")
public class gameController {

    @Autowired
    gameService gameService;

    @GetMapping
    public List<Game> allGames() {
        return gameService.getAllgames();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> startGame() {
        Game results = gameService.startGame();
        if (results == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(results);
    }

    @PostMapping("/round")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Round> startRound(@RequestBody Round round) {
        Round results = gameService.guessNumber(round);
        if (results == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/round/{id}")
    public Round sortedByTime(@PathVariable int id) {
        return gameService.sortedByTime(id);
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable int id) {
        return gameService.getGameById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGameById(@PathVariable int id) {
        gameService.deleteGameById(id);
    }
}
