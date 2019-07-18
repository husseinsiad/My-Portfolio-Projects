/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Data.LocationDaoDB.LocationMapper;
import com.superhero.superhero.Data.OrganizationDaoDB.OrganizationMapper;
import com.superhero.superhero.Model.Location;
import com.superhero.superhero.Model.Organization;
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
public class SuperheroDaoDB implements SuperheroDao {

    @Autowired
    JdbcTemplate Jdbc;

    @Override
    public List<Superhero> getAllSuperhero() {

        final String SELET_ALL_SUPERHER = "SELECT * FROM superhero";
        List<Superhero> superHero = Jdbc.query(SELET_ALL_SUPERHER, new SuperheroMapper());
        return superHero;
    }
    private List<Organization> getOrganizationForSuperHero(int id) {
        try {
            final String SELECT_ORGANIZATION_FOR_SUPERHERO = "select * from organization o join super_org so "
                    + "on so.organizationid=o.id where so.organizationid = ?";
            return Jdbc.query(SELECT_ORGANIZATION_FOR_SUPERHERO, new OrganizationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public Superhero getSuperHeroById(int id) {
        try {
            final String SELET_SUPERHER_BY_ID = "SELECT * FROM superhero where id=?";
            Superhero superhero = Jdbc.queryForObject(SELET_SUPERHER_BY_ID, new SuperheroMapper(), id);
            return superhero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Transactional
    @Override
    public Superhero addSuperHero(Superhero superhero) {
       
            final String ADD_SUPERHER = "INSERT INTO superhero(`name`,`desc`,superpower) VALUES(?,?,?)";
            Jdbc.update(ADD_SUPERHER,
                    superhero.getName(),
                    superhero.getDesc(),
                    superhero.getSuperPower());
            int newId = Jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            superhero.setId(newId);
            return superhero;

    }
 
    @Override
    @Transactional
    public void updateSuperHero(Superhero superhero) {

        final String UPDATE_SUPERHERO = "UPDATE superhero set name=?, `desc`=? ,superPower=? where id=?";
        Jdbc.update(UPDATE_SUPERHERO,
                superhero.getName(),
                superhero.getDesc(),
                superhero.getSuperPower(),
                superhero.getId());
 
    }

    @Override
    public void deleteSuperHeroById(int id) {
   
        final String DELETE_SUPERHERO_ORG = "DELETE FROM super_org WHERE superheroid = ?";
        Jdbc.update(DELETE_SUPERHERO_ORG, id);

        final String DELETE_SUPERHERO_SIDGTING = "DELETE ss.* FROM super_sighting ss join superhero s on s.id=ss.superheroid WHERE s.id = ?";
        Jdbc.update(DELETE_SUPERHERO_SIDGTING, id);

         final String DELETE_SUPERHERO = "DELETE FROM superhero WHERE id = ?";
        Jdbc.update(DELETE_SUPERHERO, id);
    }

   @Override
    public List<Location> getAllLocationBySuperHero(int id) {
        final String ALL_LOCATION_BY_SUPERHERO="select l.id,l.name,l.desc,l.address from location l join sighting s on l.id=s.locationid" +
" join super_sighting ss on s.id=ss.sightingid join superhero sh on sh.id=ss.superheroid   where sh.id=?";
        return Jdbc.query(ALL_LOCATION_BY_SUPERHERO, new LocationMapper(),id);
    }

   
      @Override
    public List<Organization> getAllOrganizationBySuperhero(int id) {
    final String ALL_ORGANIZATION_BY_SUPERHERO=" select o.id,o.name, o.desc,o.address,o.contact from organization o join super_org so on o.id=so.organizationid join superhero s on s.id=so.superheroid where s.id=?";
        return Jdbc.query(ALL_ORGANIZATION_BY_SUPERHERO,new OrganizationMapper(),id);
    }

    public static final class SuperheroMapper implements RowMapper<Superhero> {

        @Override
        public Superhero mapRow(ResultSet rs, int index) throws SQLException {
            Superhero superhero = new Superhero();
            superhero.setId(rs.getInt("id"));
            superhero.setName(rs.getString("name"));
            superhero.setDesc(rs.getString("desc"));
            superhero.setSuperPower(rs.getString("superPower"));
            return superhero;
        }
    }
}
