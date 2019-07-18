/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Model.Location;
import com.superhero.superhero.Model.Organization;
import com.superhero.superhero.Model.Superhero;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface SuperheroDao {

    List<Superhero> getAllSuperhero();

    Superhero getSuperHeroById(int id);

    Superhero addSuperHero(Superhero superhero);

    void updateSuperHero(Superhero superhero);

    void deleteSuperHeroById(int id);

   List<Organization> getAllOrganizationBySuperhero( int id);
    List<Location> getAllLocationBySuperHero(int id);

}
