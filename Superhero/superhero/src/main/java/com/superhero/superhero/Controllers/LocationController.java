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
import com.superhero.superhero.Model.Superhero;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author siyaa
 */
@Controller
public class LocationController {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;

    Set<ConstraintViolation<Location>> violations = new HashSet<>();

    @GetMapping("Location")
    public String displayLocaton(Model model) {
        List location = locationdao.getALlLocation();
        model.addAttribute("location", location);
        model.addAttribute("errors", violations);
        return "Location";
    }

    @PostMapping("AddLocation")
    public String AddLocation(String name, String desc, String address) {
        Location location = new Location();
        location.setName(name);
        location.setDesc(desc);
        location.setAddress(address);
        //Validation
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);
        if (violations.isEmpty()) {
            locationdao.addLocation(location);
        }
        return "redirect:/Location";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id) {
        locationdao.deleteLocationById(id);
        return "redirect:/Location";
    }

    @GetMapping("EditLocation")
    public String EditLocation(Integer id, Model model) {
        Location location = locationdao.getLocationById(id);
        model.addAttribute("location", location);
        return "EditLocation";
    }

    @GetMapping("LocationDetails")
    public String LocationDetails(Integer id, Model model) {
        Location location = locationdao.getLocationById(id);
        List<Superhero> superhero=locationdao.getAllSuperHeroByLocation(id);
        model.addAttribute("location", location);
        model.addAttribute("superhero",superhero);
        return "LocationDetails";
    }

    @PostMapping("EditLocation")
    public String performEditLocation(@Valid Location location, BindingResult result) {
        if(result.hasErrors()){
        return "EditLocation";
        }
        locationdao.updateLocation(location);
        return "redirect:/Location";
    }
}
