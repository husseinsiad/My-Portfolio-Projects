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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author siyaa
 */
@Controller
public class OrganizationController {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;
    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("Organization")
    public String displayOrganization(Model model) {
        List organization = organizationdao.getAllOrganization();
        List superheros = superherodao.getAllSuperhero();
        model.addAttribute("organization", organization);
        model.addAttribute("superheros", superheros);
        model.addAttribute("errors", violations);
        return "Organization";
    }

    @PostMapping("AddOrganizaton")
    public String AddOrganizaton(Organization organization, HttpServletRequest request) {
        String[] superheroid = request.getParameterValues("superheroid");
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);
        if (violations.isEmpty()) {
            List<Superhero> superherosid = new ArrayList<>();
            for (String superhero : superheroid) {
                superherosid.add(superherodao.getSuperHeroById(Integer.parseInt(superhero)));
            }
            organization.setSuperheros(superherosid);
            organizationdao.addOrganization(organization);
        }

        return "redirect:/Organization";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {
        organizationdao.deleteOrganizationById(id);
        return "redirect:/Organization";
    }

    @GetMapping("EditOrganization")
    public String EditOrganization(Integer id, Model model) {
        List<Superhero> superheros = superherodao.getAllSuperhero();
        Organization organization = organizationdao.getOrganizationById(id);

        model.addAttribute("superheros", superheros);
        model.addAttribute("organization", organization);
        return "EditOrganization";
    }

    @PostMapping("EditOrganization")
    public String performEditOrganization(@Valid Organization organization, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            return "EditOrganization";
        }
        String[] superheroid = request.getParameterValues("superheroid");
        List<Superhero> superheros = new ArrayList<>();
        if (superheroid != null) {
            for (String superhero : superheroid) {
                superheros.add(superherodao.getSuperHeroById(Integer.parseInt(superhero)));
            }
        } else {
            FieldError error = new FieldError("organization", "Organization", "Must include one Superhero");
            result.addError(error);
        }
        organization.setSuperheros(superheros);
        
        if (result.hasErrors()) {
            model.addAttribute("superheros", superherodao.getAllSuperhero());
            model.addAttribute("organization", organization);
            return "editOrganization";
        }
        organizationdao.updateOrganization(organization);
        return "redirect:/Organization";
    }

    @GetMapping("OrganizationDetails")
    public String OrganizationDetails(Integer id, Model model) {
        Organization organization = organizationdao.getOrganizationById(id);
        List<Superhero> superhero=organizationdao.getAllSuperHeroByOrganization(id);
        model.addAttribute("organization", organization);
        model.addAttribute("superhero",superhero);
        return "OrganizationDetails";
    }
}
