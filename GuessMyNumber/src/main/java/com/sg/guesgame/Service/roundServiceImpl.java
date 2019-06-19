/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Service;

import com.sg.guesgame.Data.gameDao;
import com.sg.guesgame.Data.roundDao;
import com.sg.guesgame.Model.Game;
import com.sg.guesgame.Model.Round;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author siyaa
 */
@Repository
public class roundServiceImpl implements roundService {

    @Autowired
    roundDao roundDao;

    @Override
    public Round guessNumber(Round round) {
        //Game answerKey = roundDao.getAnserKey(round.getId());
        //Declare key and gues array and initialize answer-Key and user-Guess 
//        String key[] = {round.getAnswer()};
        String key[] = {"0444"};
        String guess[] = {round.getGues()};

        //Check if answerKey is matched userGues
        if (Arrays.equals(key, guess)) {
            //String matched = "e";
            //  System.out.println("Exactly Matche: " + matched);
            return roundDao.guesNumber(round);
        } else {
            Arrays.sort(key);
            Arrays.sort(guess);
            if (Arrays.equals(key, guess)) {
                //String parMatched = "p";
                // System.out.println("Partial Matche" + parMatched);
                return roundDao.guesNumber(round);
            } else {
                //System.out.println("Un-matched");
            }
        }//else if  
        return round;
    }

    @Override
    public Round sortedByTime(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
