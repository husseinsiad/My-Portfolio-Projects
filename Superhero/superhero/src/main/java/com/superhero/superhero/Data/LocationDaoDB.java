/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Model.Location;
import com.superhero.superhero.Model.Sighting;
import com.superhero.superhero.Model.Superhero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate Jdbc;

    @Override
    public List<Location> getALlLocation() {
        try {
            final String SELET_ALL_LOCATION = "SELECT * FROM location";
            List<Location> location = Jdbc.query(SELET_ALL_LOCATION, new LocationMapper());
            return location;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Location getLocationById(int id) {
        try {
            final String SELET_LOCATION_BY_ID = "SELECT * FROM location where id=?";
            Location location = Jdbc.queryForObject(SELET_LOCATION_BY_ID, new LocationMapper(), id);
            return location;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
     
            final String ADD_LOCATION = "INSERT INTO location(`name`,`desc`,`address`) VALUES (?,?,?)";
            Jdbc.update(ADD_LOCATION,
                  
                    location.getName(),
                    location.getDesc(),
                    location.getAddress());
            int newId = Jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            location.setId(newId);
            return location;
        
    }

    @Override
    @Transactional
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location set `name`=?, `desc`=?,address=? where id=?";
        Jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDesc(),
                location.getAddress(),
                location.getId());
    }

    @Override
    public void deleteLocationById(int id) {
        
        final String DELETE_SUPER_SIGHTING = "DELETE ss.* FROM super_sighting ss join sighting s on s.id=ss.sightingid WHERE s.locationid = ?";
        Jdbc.update(DELETE_SUPER_SIGHTING, id);
        final String DELETE_SIGHTING_FOR_LOCATION = "DELETE FROM sighting WHERE locationid = ?";
        Jdbc.update(DELETE_SIGHTING_FOR_LOCATION, id);
        final String DELETE_LOCATION = "DELETE FROM location WHERE id = ?";
        Jdbc.update(DELETE_LOCATION, id);
    }

    

     @Override
    public List<Superhero> getAllSuperHeroByLocation(int id) {
        final String ALL_SUPERHERO_BY_LOCATION=" select sh.id,sh.name, sh.desc,sh.superPower from superhero sh join super_sighting ss on sh.id=ss.superheroid join sighting s on s.id=ss.sightingid join location l on l.id=s.locationid where l.id=?";
       return Jdbc.query(ALL_SUPERHERO_BY_LOCATION, new SuperheroDaoDB.SuperheroMapper(),id);
    }
    
     @Override
    public List<Sighting> getAllSightingByLocation(int id) {
        final String All_Sighting_By_Location = "select * from sighting s join location l on l.id=s.locationid where l.id=?";
        return Jdbc.query(All_Sighting_By_Location, new SightingDaoDB.SightingMapper(), id);
    }
   

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setName(rs.getString("name"));
            location.setDesc(rs.getString("desc"));
            location.setAddress(rs.getString("address"));
            return location;
        }
    }

}
