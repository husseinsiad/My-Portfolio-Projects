/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Service;

import com.sg.guesgame.Data.gameDao;
import com.sg.guesgame.Model.Game;
import com.sg.guesgame.Model.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author siyaa
 */
@Repository
public class gameServiceImpl implements gameService {

    @Autowired
    gameDao gameDao;

    @Override
    public Game startGame() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < 4) {
            int random = randomGenerator.nextInt(10);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        String getAnswer = "";
        for (int num : numbers) {
            getAnswer = getAnswer + num;
        }

        Game game = new Game();
        game.setAnswer(getAnswer);
        return gameDao.startGame(game);
    }

    @Override
    public Game getGameById(int id) {

        return gameDao.getGameById(id);
    }

    @Override
    public List<Game> getAllgames() {
        return gameDao.getAllgames();
    }

    @Override
    public Round guessNumber(Round round) {
        Round currentRound = new Round();
        Game answerKey = gameDao.getGameById(round.getGameInfo().getId());
        //Declare key and gues array and initialize answer-Key and user-Guess 
        // String key[] = {round.getAnswer()};
        String key[] = answerKey.getAnswer().split("");
        String guess[] = round.getGues().split("");
        int matched = 0;
        int partial = 0;
        for (int i = 0; i < key.length; i++) {
            if (key[i].equals(guess[i])) {
                matched++;
            } else {
                for (int j = 0; j < guess.length; j++) {
                    if (key[i].equals(guess[j])) {
                        partial++;
                    }

                }
            }
        } 
          //format  "e:0:p:0" 
        String results="e:"+ matched+"p:" + partial;
        currentRound.setTime(LocalDateTime.now());
        currentRound.setGues(round.getGues());
        currentRound.setResult(results);
        currentRound.setGameInfo(round.getGameInfo());
        Game game= new Game();
        game.setStatus(true); // Finished
        game.setId(answerKey.getId());
        //Update Game Status' Finished'
         updateGame(game);
        return gameDao.guessNumber(currentRound);
        
 
    }

    @Override
    public void updateGame(Game game) {
     gameDao.updateGame(game); }

    @Override
    public void deleteGameById(int id) {
        gameDao.deleteGameById(id);
    }
    
}
 
