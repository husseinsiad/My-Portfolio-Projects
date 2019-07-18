/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Model.Organization;
import com.superhero.superhero.Model.Superhero;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface OrganizationDao {

    List<Organization> getAllOrganization();

    Organization getOrganizationById(int id);

    Organization addOrganization(Organization organization);

    void updateOrganization(Organization organization);

    void deleteOrganizationById(int id);

    
     List<Superhero> getAllSuperHeroByOrganization( int id);
}
