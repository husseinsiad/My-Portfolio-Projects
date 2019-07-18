/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Controllers;

import com.superhero.superhero.Data.LocationDao;
import com.superhero.superhero.Data.OrganizationDao;
import com.superhero.superhero.Data.SightingDao;
import com.superhero.superhero.Data.SuperheroDao;
import com.superhero.superhero.Model.Location;
import com.superhero.superhero.Model.Sighting;
import com.superhero.superhero.Model.Superhero;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author siyaa
 */
@Controller
public class NewsFeedController {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;

      @GetMapping("Newsfeed")
    public String displayTopTenSighting(Model model) {
        List<Sighting> sighting = sightingdao.getTopTenSighting();
        model.addAttribute("sighting", sighting);
        return "Newsfeed";
    }
}
