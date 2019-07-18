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
import com.superhero.superhero.Model.Organization;
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
public class SuperheroController {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;

    Set<ConstraintViolation<Superhero>> violations = new HashSet<>();

    @GetMapping("Superhero")
    public String displaySuperHero(Model model) {
        List superheros = superherodao.getAllSuperhero();
        model.addAttribute("superheros", superheros);
        model.addAttribute("errors", violations);
        return "Superhero";
    }

    @PostMapping("AddSuperhero")
    public String addSuperhero(String name, String superpower, String desc) {
        Superhero superhero = new Superhero();
        superhero.setName(name);
        superhero.setDesc(desc);
        superhero.setSuperPower(superpower);
        //Validation
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superhero);
        if (violations.isEmpty()) {
            superherodao.addSuperHero(superhero);
        }

        return "redirect:/Superhero";
    }

    @GetMapping("deleteSuperHero")
    public String deleteSuperHero(Integer id) {
        superherodao.deleteSuperHeroById(id);
        return "redirect:/Superhero";
    }

    @GetMapping("EditSuperhero")
    public String editSuperHero(Integer id, Model model) {
        Superhero superhero = superherodao.getSuperHeroById(id);
        model.addAttribute("superhero", superhero);
        return "EditSuperhero";
    }

    @PostMapping("EditSuperhero")
    public String performEditSuperHero(@Valid Superhero superhero, BindingResult result) {
        if (result.hasErrors()) {
            return "EditSuperhero";
        }
        superherodao.updateSuperHero(superhero);
        return "redirect:/Superhero";
    }

    @GetMapping("SuperheroDetail")
    public String SuperheroDetail(Integer id, Model model) {
        Superhero superhero = superherodao.getSuperHeroById(id);
        List<Location> locationBySuperhero=superherodao.getAllLocationBySuperHero(id);
        List<Organization> organization=superherodao.getAllOrganizationBySuperhero(id);
        model.addAttribute("superhero", superhero);
        model.addAttribute("location",locationBySuperhero);
        model.addAttribute("organization",organization);
        return "SuperheroDetail";
    }
}
