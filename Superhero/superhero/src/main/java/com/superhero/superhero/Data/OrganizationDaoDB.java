/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Data.SuperheroDaoDB.SuperheroMapper;
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
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Organization> getAllOrganization() {
        final String GET_ALL_ORGANIZATION = "SELECT * FROM organization";
        List<Organization> organization = jdbc.query(GET_ALL_ORGANIZATION, new OrganizationMapper());
        associateSuperhero(organization);
        return organization;
    }

    //Helper Method
    private void associateSuperhero(List<Organization> organization) {
        for (Organization organizations : organization) {
            organizations.setSuperheros(getSuperheroForOrganization(organizations.getId()));
        }
    }

    //Helper Method
    private List<Superhero> getSuperheroForOrganization(int id) {
    
            final String GET_SUPERHERO_FOR_ORGANIZATION = "select * from superhero s join super_org so "
                    + "on so.superheroid=s.id where so.organizationid = ?";
            return jdbc.query(GET_SUPERHERO_FOR_ORGANIZATION, new SuperheroMapper(), id);
        
    }

    @Override
    public Organization getOrganizationById(int id) {
      try{
          final String GET_ORGANIZATION_BY_ID = "SELECT * FROM organization where id=?";
            Organization organization = jdbc.queryForObject(GET_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
            organization.setSuperheros(getSuperheroForOrganization(id));
            return organization;
      }catch(EmptyResultDataAccessException ex){
          return null;
      }
            
        

    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {

        final String ADD_ORGANIZATION = "INSERT INTO organization (`name`,`desc`,address,contact) VALUES(?,?,?,?)";
        jdbc.update(ADD_ORGANIZATION,
                organization.getName(),
                organization.getDesc(),
                organization.getAddress(),
                organization.getContact());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        insertSuperOrganization(organization);
        return organization;

    }

    //Helper Method
    private void insertSuperOrganization(Organization organization) {
        for (Superhero superhero : organization.getSuperheros()) {
            final String INSERT_SUPER_ORG = "INSERT INTO super_org (superheroid,organizationid) VALUES(?,?)";
            jdbc.update(INSERT_SUPER_ORG,
                    superhero.getId(),
                    organization.getId());

        }
    }

    @Override
    //@Transactional
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organization set name=?, `desc`=?,address=?,contact=? where id=?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDesc(),
                organization.getAddress(),
                organization.getContact(),
                organization.getId());
        final String DELETE_SUPERHERO_ORG = "DELETE FROM super_org WHERE organizationid = ?";
        jdbc.update(DELETE_SUPERHERO_ORG, organization.getId());
        insertSuperOrganization(organization);
    }

    @Override
    public void deleteOrganizationById(int id) {
        final String DELETE_SUPERHERO_ORG = "DELETE FROM super_org WHERE organizationid = ?";
        jdbc.update(DELETE_SUPERHERO_ORG, id);

        final String DELETE_ORGANIZATION = "DELETE FROM organization WHERE id = ?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }

   @Override
    public List<Superhero> getAllSuperHeroByOrganization(int id) {
   final String ALL_SUPERHERO_BY_ORGANIZATION=" select s.id,s.name, s.desc,s.superPower  from superhero s join super_org so on s.id=so.superheroid join organization o on o.id=so.organizationid where o.id=?";
       return jdbc.query(ALL_SUPERHERO_BY_ORGANIZATION, new SuperheroMapper(),id);
    }

  

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setDesc(rs.getString("desc"));
            organization.setAddress(rs.getString("address"));
            organization.setContact(rs.getString("contact"));
            return organization;
        }
    }

}
