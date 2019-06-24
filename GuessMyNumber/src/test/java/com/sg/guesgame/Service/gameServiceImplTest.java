/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Service;

import com.sg.guesgame.Model.Game;
import com.sg.guesgame.Model.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author siyaa
 */
@Profile("test")
public class gameServiceImplTest {

    @Autowired
    gameService gameService;

    public gameServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//          List<Game> games = gameService.getAllgames();
//        for (Game game : games) {
//            gameService.deleteGameById(game.getId());
//        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of startGame method, of class gameServiceImpl.
     */
    @Test
    public void testStartGame() {
    }

   /**
     * Test of testAddGetGameById method, of class gameServiceImpl.
     */
    @Test
    public void testAddGetGameById() {
        Game game = new Game();
        game.setAnswer("1436");
        game.setStatus(false);
        gameService.startGame();
        Game toReturn = gameService.getGameById(game.getId());
        assertEquals(game, toReturn);
    }
  

        /**
     * Test of guessNumber method, of class gameServiceImpl.
     */
    @Test
    public void testGuessNumber() {
        Game game = new Game();
        game.setId(1);
        game.setAnswer("2345");
        game.setStatus(false);
        gameService.startGame();
        
        Round round=new Round();
        round.setGameInfo(game);
        round.setGues(game.getAnswer());
        Round fromDao=gameService.guessNumber(round);
        assertEquals(1,fromDao.getId());
        assertEquals(game.getAnswer(),fromDao.getId());
        assertEquals(1,fromDao.getId());
        

    }



}
