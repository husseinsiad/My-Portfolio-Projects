/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Data;

import com.sg.guesgame.Model.Game;
import com.sg.guesgame.TestApplicationConfiguration;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author siyaa
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)

@Profile("Development")
public class gameDaoImplTest {

    @Autowired
    gameDao gameDao;

    public gameDaoImplTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Game> games = gameDao.getAllgames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of testAddGetGameById method, of class gameDaoImpl.
     */
    @Test
    public void testAddGetGameById() {
        Game game = new Game();
        game.setAnswer("1436");
        game.setStatus(false);
        gameDao.startGame(game);
        Game toReturn = gameDao.getGameById(game.getId());
        assertEquals(game, toReturn);
    }

    /**
     * Test of getAllgames method, of class gameDaoImpl.
     */
    @Test
    public void testGetAllgames() {

        Game game1 = new Game();
        game1.setAnswer("2345");
        game1.setStatus(false);
        //Start Game
        gameDao.startGame(game1);

        Game game2 = new Game();
        game2.setAnswer("9876");
        game2.setStatus(false);
        //Start Game
        gameDao.startGame(game2);

        List<Game> allGames = gameDao.getAllgames();
        assertEquals(2, allGames.size());
        assertTrue(allGames.contains(game1));
        assertTrue(allGames.contains(game2));
    }

    /**
     * Test of updateGame method, of class gameDaoImpl.
     */
    @Test
    public void testUpdateGame() {
        Game game1 = new Game();
        game1.setAnswer("2345");
        game1.setStatus(false);
        gameDao.startGame(game1);

        Game toEdit = gameDao.getGameById(game1.getId());
        Game game2 = new Game();
        game2.setId(toEdit.getId());
        game2.setAnswer("1111");
        game2.setStatus(false);
        gameDao.updateGame(game2);
        Game fromDao = gameDao.getGameById(game2.getId());
        assertNotEquals(game2, fromDao);
        assertEquals(game1, fromDao);
    }

//    /**
//     * Test of guessNumber method, of class gameDaoImpl.
//     */
//    @Test
//    public void testGuessNumber() {
//        Game game = new Game();
//        game.setId(1);
//        game.setAnswer("2345");
//        game.setStatus(false);
//        gameDao.startGame(game);
//        
//        Round round=new Round();
//        round.setGameInfo(game);
//        round.setGues(game.getAnswer());
//        Round fromDao=gameDao.guessNumber(round);
//        assertEquals(1,fromDao.getId());
//        assertEquals(game.getAnswer(),fromDao.getId());
//        assertEquals(1,fromDao.getId());
//        
//
//    }
//
}
