/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Model.Location;
import com.superhero.superhero.Model.Sighting;
import com.superhero.superhero.Model.Superhero;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface LocationDao {

    List<Location> getALlLocation();

    Location getLocationById(int id);

    Location addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocationById(int id);

    List<Superhero> getAllSuperHeroByLocation(int id);

    List<Sighting> getAllSightingByLocation(int id);

}
