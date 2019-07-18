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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
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
public class SightingController {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;

    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();

    @GetMapping("Sighting")
    public String displaySighting(Model model) {
        List<Sighting> sighting = sightingdao.getAllSighting();
        List superheros = superherodao.getAllSuperhero();
        List locations = locationdao.getALlLocation();
        model.addAttribute("sighting", sighting);
        model.addAttribute("locations", locations);
        model.addAttribute("superheros", superheros);
        return "Sighting";
    }

    @PostMapping("AddSighting")
    public String AddSighting(HttpServletRequest request) {
        String locations = request.getParameter("locationid");
        String[] superheros = request.getParameterValues("superheroid");
        //Date
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        Sighting sighting = new Sighting();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate sDate = LocalDate.parse(date, dateTimeFormatter);
        LocalTime sTime = LocalTime.parse(time, formatter);
        LocalDateTime datetime = LocalDateTime.of(sDate, sTime);

        List<Superhero> superheroid = new ArrayList<>();
        for (String superhero : superheros) {
            superheroid.add(superherodao.getSuperHeroById(Integer.parseInt(superhero)));
        }

        sighting.setLocations(locationdao.getLocationById(Integer.parseInt(locations)));
        sighting.setSuperheros(superheroid);
        sighting.setDate(datetime);
        sightingdao.addSighting(sighting);
        return "redirect:/Sighting";

    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingdao.deleteSightingById(id);
        return "redirect:/Sighting";
    }

    @GetMapping("EditSighting")
    public String EditSighting(Model model, Integer id) {
        List<Location> location = locationdao.getALlLocation();
        List<Superhero> superheros = superherodao.getAllSuperhero();
        Sighting sighting = sightingdao.getSightingById(id);
        LocalDate date = sighting.getDate().toLocalDate();
        LocalTime time = sighting.getDate().toLocalTime();
        model.addAttribute("location", location);
        model.addAttribute("superheros", superheros);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("sighting", sighting);
        return "EditSighting";
    }

    @PostMapping("EditSighting")
    public String performEditSighting(@Valid Sighting sighting, HttpServletRequest request, BindingResult result,Model model) {
        String date = request.getParameter("sightingdate");
        String time = request.getParameter("time");
      //  Sighting sighting = new Sighting();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDate sDate = LocalDate.parse(date, dateTimeFormatter);
        LocalTime sTime = LocalTime.parse(time, formatter);
        LocalDateTime datetime = LocalDateTime.of(sDate, sTime);
        if (result.hasErrors()) {
            return "EditSighting";
        }
        //Date

        String[] superheroid = request.getParameterValues("superheroid");
        String locationid = request.getParameter("locationid");
        List<Superhero> superheros = new ArrayList<>();
        if (superheroid != null) {
            for (String superhero : superheroid) {
                superheros.add(superherodao.getSuperHeroById(Integer.parseInt(superhero)));
            }
        }
        else {
            FieldError error = new FieldError("sighting", "Superhro", "Must include one Superhero");
            result.addError(error);
        }

        sighting.setSuperheros(superheros);
        sighting.setLocations(locationdao.getLocationById(Integer.parseInt(locationid)));
        sighting.setDate(datetime);
        if (result.hasErrors()) {

            model.addAttribute("location", locationdao.getALlLocation());
            model.addAttribute("superheros", superherodao.getAllSuperhero());
            model.addAttribute("sightng", sighting);
            return "editSighting";
        }

        sightingdao.updateSighting(sighting);
        return "redirect:/Sighting";
    }

    @GetMapping("SightingDetails")
    public String SightingDetails(Integer id, Model model) {
        Sighting sighting = sightingdao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "SightingDetails";
    }

    @GetMapping("SightingForDate")
    public String SightingForDate(Model model, HttpServletRequest request) {
        String date = request.getParameter("date");
        String time = request.getParameter("time");
//         Sighting sighting=new Sighting();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate sDate = LocalDate.parse(date, dateTimeFormatter);
        LocalTime sTime = LocalTime.parse(time, formatter);
        LocalDateTime datetime = LocalDateTime.of(sDate, sTime);
        List<Sighting> sightingDate = sightingdao.getAllSightingByDate(datetime);
        model.addAttribute("sightingDate", sightingDate);
        return "Sighting";
    }

}
