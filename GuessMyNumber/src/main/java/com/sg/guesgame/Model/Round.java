/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Model;

import java.time.LocalDateTime;
/**
 *
 * @author siyaa
 */
public class Round {
    private int id;
    private LocalDateTime time;
    private String gues;
    private String result;
    private Game gameInfo;
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getGues() {
        return gues;
    }

    public void setGues(String gues) {
        this.gues = gues;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Game getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(Game gameInfo) {
        this.gameInfo = gameInfo;
    }
    
}
