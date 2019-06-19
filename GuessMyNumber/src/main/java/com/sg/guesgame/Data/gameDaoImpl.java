/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guesgame.Data;

import com.sg.guesgame.Model.Game;
import com.sg.guesgame.Model.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author siyaa
 */
@Repository
//@Profile("production")
public class gameDaoImpl implements gameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Game startGame(Game game) {
        final String sql = "INSERT INTO Game(answer) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }
    
    @Override
    public Round guessNumber(Round round) {
        final String sql = "INSERT INTO round(time,gues,result,gameid) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
           // round.setTime(Timestamp.valueOf(round.getTime()).toLocalDateTime());
            statement.setString(1, round.getTime().toString());
             statement.setString(2, round.getGues());
            statement.setString(3, round.getResult());
            statement.setObject(4, round.getGameInfo().getId());
            return statement;
        }, keyHolder);
        
        round.setId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public Game getGameById(int id) {
        String GET_GAME_BY_ID = "SELECT * FROM Game where id= ?";
        return jdbc.queryForObject(GET_GAME_BY_ID, new gameMap(), id);

    }

    @Override
    public List<Game> getAllgames() {
        String GET_ALL_GAME = "SELECT * FROM game";
        return jdbc.query(GET_ALL_GAME, new gameMap());
    }

    @Override
    public void updateGame(Game game) {
        String UPDATE_GAME_STATUS="UPDATE game SET status= ? WHERE id= ?;";
        jdbc.update(UPDATE_GAME_STATUS,
                game.getStatus(),
                game.getId());
    }

    @Override
    public void deleteGameById(int id) {
        
       String DELETE_ROUND_BY_ID = "DELETE FROM round where gameid= ?";
      jdbc.update(DELETE_ROUND_BY_ID, id);
      String DELETE_GAME_BY_ID = "DELETE FROM Game where id= ?";
      jdbc.update(DELETE_GAME_BY_ID, id);
    }

    private final static class gameMap implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(rs.getBoolean("status"));
            return game;
        }

    }

}
