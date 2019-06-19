/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Service;

import com.sg.guesgame.Model.Round;

/**
 *
 * @author siyaa
 */
public interface roundService {
    Round sortedByTime(int id);
    Round guessNumber(Round round);
}
