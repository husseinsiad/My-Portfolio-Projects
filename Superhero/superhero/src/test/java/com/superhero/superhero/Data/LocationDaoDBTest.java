/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Data;

import com.superhero.superhero.Model.Location;
import com.superhero.superhero.Model.Organization;
import com.superhero.superhero.Model.Sighting;
import com.superhero.superhero.Model.Superhero;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author siyaa
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDaoDBTest {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;

    public LocationDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Location> locations = locationdao.getALlLocation();
        for (Location location : locations) {
            locationdao.deleteLocationById(location.getId());
        }
        List<Superhero> superheros = superherodao.getAllSuperhero();
        for (Superhero superhero : superheros) {
            superherodao.deleteSuperHeroById(superhero.getId());
        }
        List<Organization> organizations = organizationdao.getAllOrganization();
        for (Organization organization : organizations) {
            organizationdao.deleteOrganizationById(organization.getId());
        }

        List<Sighting> sightings = sightingdao.getAllSighting();
        for (Sighting sighting : sightings) {
            sightingdao.deleteSightingById(sighting.getId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getALlLocation method, of class LocationDaoDB.
     */
    @Test
    public void testGetALlLocation() {
        Location location1 = new Location();
        location1.setName("Bloomignton");
        location1.setDesc("La Fitnes");
        location1.setAddress("pene ave 123");
        location1 = locationdao.addLocation(location1);

        Location location2 = new Location();
        location2.setName("SaintPaul");
        location2.setDesc("sp College");
        location2.setAddress("Marshel ave 123");
        location2 = locationdao.addLocation(location2);

        List<Location> fromDao = locationdao.getALlLocation();
        assertEquals(2, fromDao.size());
        assertTrue(fromDao.contains(location1));
        assertTrue(fromDao.contains(location2));

    }

    /**
     * Test of testGetAddLocation method, of class LocationDaoDB.
     */
    @Test
    public void testGetAddLocation() {
        Location location = new Location();
        location.setName("Karmel");
        location.setDesc("Karmel Mall");
        location.setAddress("lake st 123");
        location = locationdao.addLocation(location);

        Location fromDao = locationdao.getLocationById(location.getId());
        assertEquals(fromDao, location);
    }

    /**
     * Test of updateLocation method, of class LocationDaoDB.
     */
    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Karmel");
        location.setDesc("Karmel Mall");
        location.setAddress("lake st 123");
        location = locationdao.addLocation(location);

        Location fromDao = locationdao.getLocationById(location.getId());
        assertEquals(fromDao, location);

        Location toEdit = locationdao.getLocationById(location.getId());
        toEdit.setName("Bloomignton");
        toEdit.setDesc("La Fitnes");
        toEdit.setAddress("pene ave 123");
        locationdao.updateLocation(toEdit);

        assertNotEquals(toEdit, location);

        fromDao = locationdao.getLocationById(location.getId());
        assertEquals(toEdit, fromDao);
    }

    /**
     * Test of deleteLocationById method, of class LocationDaoDB.
     */
    @Test
    public void testDeleteLocationById() {
        Location location = new Location();
        location.setName("Karmel");
        location.setDesc("Karmel Mall");
        location.setAddress("lake st 123");
        location = locationdao.addLocation(location);

        Location toDelete = locationdao.getLocationById(location.getId());
        locationdao.deleteLocationById(toDelete.getId());
        Location fromDao = locationdao.getLocationById(location.getId());
        assertNull(fromDao);
    }

      /**
     * Test of getAllSuperHeroByLocation method, of class SuperheroDaoDB.
     */
    @Test
    public void getAllSuperHeroByLocation() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);
        List<Superhero> superheros= new ArrayList<>();
        superheros.add(superhero);
        
        Location location=new Location();
        location.setName("Karmel");
        location.setDesc("karmel Mal");
        location.setAddress("Lake street");
        locationdao.addLocation(location);
        
        Sighting sighting=new Sighting();
        sighting.setDate(LocalDateTime.now().withNano(0));
        sighting.setLocations(location);
        sighting.setSuperheros(superheros);
        sightingdao.addSighting(sighting);
        
        List<Superhero> fromDao=locationdao.getAllSuperHeroByLocation(location.getId());
        
        assertEquals(1,fromDao.size());
        assertTrue(fromDao.contains(superhero));
    }
    
     /**
     * Test of getAllSightingByLocation method, of class SuperheroDaoDB.
     */
    @Test
    public void getAllSightingByLocation() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);
        List<Superhero> superheros= new ArrayList<>();
        superheros.add(superhero);
        
        Organization organization =new Organization();
        organization.setName("SWG");
        organization.setDesc("Software Guild");
        organization.setAddress("15 50th");
        organization.setContact("83838");
        organization.setSuperheros(superheros);
        organizationdao.addOrganization(organization);
        
        Location location=new Location();
        location.setName("Karmel");
        location.setDesc("karmel Mal");
        location.setAddress("Lake street");
        locationdao.addLocation(location);
        
        Sighting sighting=new Sighting();
        sighting.setDate(LocalDateTime.now().withNano(0));
        sighting.setLocations(location);
        sighting.setSuperheros(superheros);
        sightingdao.addSighting(sighting);
        
        List<Sighting> fromDao=locationdao.getAllSightingByLocation(location.getId());

        assertEquals(1,fromDao.size());
        //assertTrue(fromDao.contains(sighting));
    }
}
