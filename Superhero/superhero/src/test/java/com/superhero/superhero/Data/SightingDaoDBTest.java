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
public class SightingDaoDBTest {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;

    public SightingDaoDBTest() {
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
     * Test of getAllSighting method, of class SightingDaoDB.
     */
    @Test
    public void testGetAllSighting() {
        Location location = new Location();
        location.setName("SaintPaul");
        location.setDesc("sp College");
        location.setAddress("Marshel ave 123");
        location = locationdao.addLocation(location);

        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDateTime.now().withNano(0));
        sighting.setLocations(location);
        sighting.setSuperheros(superheros);
        sightingdao.addSighting(sighting);

        List<Sighting> fromDao = sightingdao.getAllSighting();
        assertEquals(1, fromDao.size());
    }

    /**
     * Test of testGetAddSighting method, of class SightingDaoDB.
     */
    @Test
    public void testGetAddSighting() {

        Location location = new Location();
        location.setName("SaintPaul");
        location.setDesc("sp College");
        location.setAddress("Marshel ave 123");
        location = locationdao.addLocation(location);

        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);

        Sighting sighting = new Sighting();

        sighting.setDate(LocalDateTime.now().withNano(0));
        sighting.setLocations(location);
        sighting.setSuperheros(superheros);

        Sighting fromDao = sightingdao.addSighting(sighting);
        assertEquals(fromDao, sighting);

    }

    /**
     * Test of updateSighting method, of class SightingDaoDB.
     */
    @Test
    public void testUpdateSighting() {
        Location location = new Location();
        location.setName("Minneapolis");
        location.setDesc("MCTC");
        location.setAddress("Henabein ave 123");
        location = locationdao.addLocation(location);

        Location location1 = new Location();
        location1.setName("Golden Vale");
        location1.setDesc("Walmar Area");
        location1.setAddress("xxx ave 123");
        location1 = locationdao.addLocation(location1);

        Superhero superhero = new Superhero();
        superhero.setName("BlackWindow");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        Superhero superhero1 = new Superhero();
        superhero1.setName("Storm");
        superhero1.setDesc("a comic");
        superhero1.setSuperPower("fictional superhe");
        superhero1 = superherodao.addSuperHero(superhero1);

        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);
        List<Superhero> superheros1 = new ArrayList<>();
        superheros.add(superhero1);

        Sighting sighting = new Sighting();

        sighting.setDate(LocalDateTime.now().withNano(0));
        sighting.setLocations(location);
        sighting.setSuperheros(superheros);
        Sighting fromDao = sightingdao.addSighting(sighting);
        assertEquals(fromDao, sighting);

        // Get Data you want to Update
        Sighting toEdit = sightingdao.getSightingById(sighting.getId());
        // Update data 
        toEdit.setDate(LocalDateTime.now().withNano(0));
        toEdit.setLocations(location1);
        toEdit.setSuperheros(superheros1);
        sightingdao.updateSighting(toEdit);

        assertNotEquals(toEdit, sighting);

    }

    /**
     * Test of deleteSightingById method, of class SightingDaoDB.
     */
    @Test
    public void testDeleteSightingById() {

        Location location = new Location();
        location.setName("Minneapolis");
        location.setDesc("MCTC");
        location.setAddress("Henabein ave 123");
        location = locationdao.addLocation(location);

        Superhero superhero = new Superhero();
        superhero.setName("BlackWindow");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);

        Sighting sighting = new Sighting();

        sighting.setDate(LocalDateTime.now().withNano(0));
        sighting.setLocations(location);
        sighting.setSuperheros(superheros);

        sightingdao.addSighting(sighting);

        // Get Data you want to Delete
        Sighting toDelete = sightingdao.getSightingById(sighting.getId());
        // Delete data 
        sightingdao.deleteSightingById(toDelete.getId());
        Sighting fromDao = sightingdao.getSightingById(sighting.getId());
        assertNull(fromDao);

    }
    
   
    
    /**
     * Test of getAllSightingByDate method, of class SuperheroDaoDB.
     */
    @Test
    public void getAllSightingByDate() {
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
        
        List<Sighting> fromDao=sightingdao.getAllSightingByDate(sighting.getDate());

        //assertEquals(1,fromDao.size());
      //  assertTrue(fromDao.contains(sighting));
    }


}
