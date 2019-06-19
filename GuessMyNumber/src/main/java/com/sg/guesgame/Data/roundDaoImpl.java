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
//@Profile("database")
public class roundDaoImpl implements roundDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Round guesNumber(Round round) {
        final String sql = "INSERT INTO round(time,gues,result,gameid) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(2, round.getGues());
            statement.setString(3, round.getResult());
            statement.setObject(4, round.getGameInfo());
            return statement;
        }, keyHolder);
        round.setTime(Timestamp.valueOf(round.getTime()).toLocalDateTime());
        round.setId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public Round sortedByTime(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Game getAnserKey(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
//     private final static class roundMap implements RowMapper<Round> {
//
//        @Override
//        public Round mapRow(ResultSet rs, int i) throws SQLException {
//            Round round = new Round();
//            round.setId(rs.getInt("id"));
//            round.setTime(rs.getTimestamp("time").toLocalDateTime());
//            round.setGues(rs.getString("gues"));
//             round.setResult(rs.getString("result"));
//             //round.setGameInfo(rs.getString("gues").toString());
//            //return game;
//        }
//
//    }

}
