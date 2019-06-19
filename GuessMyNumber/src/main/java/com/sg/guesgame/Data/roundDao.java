/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Data;

import com.sg.guesgame.Model.Game;
import com.sg.guesgame.Model.Round;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface roundDao {

    Round sortedByTime(int id);
    Round guesNumber(Round round);
    Game getAnserKey(int id);
}
