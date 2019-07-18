/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Data.LocationDaoDB.LocationMapper;
import com.superhero.superhero.Data.SuperheroDaoDB.SuperheroMapper;
import com.superhero.superhero.Model.Location;
import com.superhero.superhero.Model.Sighting;
import com.superhero.superhero.Model.Superhero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author siyaa
 */
@Repository
public class SightingDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override

    public List<Sighting> getAllSighting() {

        final String SELET_ALL_SIGHTING = "SELECT * FROM sighting";
        List<Sighting> sighting = jdbc.query(SELET_ALL_SIGHTING, new SightingMapper());
        associateLocationAndSuperhero(sighting);

        return sighting;
    }

    //Helper Method
    private void associateLocationAndSuperhero(List<Sighting> sighting) {
        for (Sighting sight : sighting) {
            sight.setLocations(getLocationForSight(sight.getId()));
            sight.setSuperheros(getSuperheroForSight(sight.getId()));
        }

    }

    //Helper Method
    private Location getLocationForSight(int id) {
        final String GET_LOCATION_FOR_SIGHT = "select * from location l join sighting s on l.id=s.locationid where s.id=?";
        return jdbc.queryForObject(GET_LOCATION_FOR_SIGHT, new LocationMapper(), id);
    }

    //Helper Methd
    private List<Superhero> getSuperheroForSight(int id) {
        final String GET_SUPERHERO_FOR_SIGHTING = "select * from superhero s join super_sighting ss "
                + "on ss.superheroid=s.id where ss.sightingid=?";
        return jdbc.query(GET_SUPERHERO_FOR_SIGHTING, new SuperheroMapper(), id);

    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String GET_SIGHTING_BY_ID = "SELECT * FROM sighting WHERE id=?";
            Sighting sighting = jdbc.queryForObject(GET_SIGHTING_BY_ID, new SightingMapper(), id);
            sighting.setLocations(getLocationForSight(id));
            sighting.setSuperheros(getSuperheroForSight(id));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {

        final String ADD_SIGHTING = "INSERT INTO sighting (date,locationid) VALUES(?,?)";
        jdbc.update(ADD_SIGHTING,
                sighting.getDate(),
                sighting.getLocations().getId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setId(newId);
        insertSuperSight(sighting);
        return sighting;

    }

    //helper Method
    private void insertSuperSight(Sighting sighting) {
        for (Superhero superhero : sighting.getSuperheros()) {
            final String INSERT_SUPER_SIGHTING = "INSERT INTO super_sighting (superheroid,sightingid) VALUES(?,?)";
            jdbc.update(INSERT_SUPER_SIGHTING,
                    superhero.getId(),
                    sighting.getId());

        }
    }

    @Override
    @Transactional
    public void updateSighting(Sighting Sighting) {
        final String UPDATE_SIGHTING = "UPDATE sighting set date=?, locationid=? where id=?";
        jdbc.update(UPDATE_SIGHTING,
                Timestamp.valueOf(Sighting.getDate()),
                Sighting.getLocations().getId(),
                Sighting.getId());
        final String DELETE_SUPERHERO_SIGHTING = "DELETE FROM super_sighting WHERE sightingid = ?";
        jdbc.update(DELETE_SUPERHERO_SIGHTING, Sighting.getId());
        insertSuperSight(Sighting);
    }

    @Override
    public void deleteSightingById(int id) {
        final String DELETE_SUPER_SIGHT_BY_ID = "DELETE FROM super_sighting where sightingid=?";
        jdbc.update(DELETE_SUPER_SIGHT_BY_ID, id);

        final String DELETE_SIHGTING_BY_ID = "DELETE FROM sighting where id=?";
        jdbc.update(DELETE_SIHGTING_BY_ID, id);
    }

   

    @Override
    public List<Sighting> getAllSightingByDate(LocalDateTime date) {
         final String SELET_ALL_SIGHTING = " select id,date from sighting where date like '2019-07-10%'";
        List<Sighting> sighting = jdbc.query(SELET_ALL_SIGHTING, new SightingMapper());
        associateLocationAndSuperhero(sighting);

        return sighting;
    }

    public List<Sighting> getTopTenSighting(){
        final String SELET_ALL_SIGHTING = "SELECT * FROM sighting order by date limit 10";
        List<Sighting> sighting = jdbc.query(SELET_ALL_SIGHTING, new SightingMapper());
        associateLocationAndSuperhero(sighting);

        return sighting;
    }
    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("id"));
            sighting.setDate(rs.getTimestamp("date").toLocalDateTime());
            return sighting;
        }
    }
}
