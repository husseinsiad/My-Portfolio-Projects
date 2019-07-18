/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Model.Sighting;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface SightingDao {

    List<Sighting> getAllSighting();

    Sighting getSightingById(int id);

    Sighting addSighting(Sighting sighting);

    void updateSighting(Sighting Sighting);

    void deleteSightingById(int id);

    List<Sighting> getAllSightingByDate(LocalDateTime date);
    List<Sighting> getTopTenSighting();
}
