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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class SuperheroDaoDBTest {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;

    public SuperheroDaoDBTest() {
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
     * Test of getAllSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetAllSuperhero() {
        Superhero superhero1 = new Superhero();
        superhero1.setName("SuperMan");
        superhero1.setDesc("a comic book");
        superhero1.setSuperPower("fictional superhe");
        superhero1 = superherodao.addSuperHero(superhero1);

        Superhero superhero2 = new Superhero();
        superhero2.setName("Storm");
        superhero2.setDesc(" Marvel Comics");
        superhero2.setSuperPower("fictional superhe");
        superhero2 = superherodao.addSuperHero(superhero2);

        List<Superhero> fromDao = superherodao.getAllSuperhero();
        assertEquals(2, fromDao.size());
        assertTrue(fromDao.contains(superhero1));
        assertTrue(fromDao.contains(superhero2));
    }

    /**
     * Test of testGetAddSuperHero method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetAddSuperHero() {
        Superhero superhero1 = new Superhero();
        superhero1.setName("SuperMan");
        superhero1.setDesc("a comic book");
        superhero1.setSuperPower("fictional superhe");
        superhero1 = superherodao.addSuperHero(superhero1);
        Superhero fromDao = superherodao.getSuperHeroById(superhero1.getId());

        assertEquals(fromDao, superhero1);
    }

    /**
     * Test of updateSuperHero method, of class SuperheroDaoDB.
     */
    @Test
    public void testUpdateSuperHero() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        Superhero fromDao = superherodao.getSuperHeroById(superhero.getId());
        assertEquals(fromDao, superhero);

        Superhero toEdit = superherodao.getSuperHeroById(superhero.getId());
        toEdit.setName("Black Window");
        toEdit.setDesc("Natalia Alianovna");
        toEdit.setSuperPower("spy");
        superherodao.updateSuperHero(toEdit);

        assertNotEquals(toEdit, superhero);

        fromDao = superherodao.getSuperHeroById(superhero.getId());
        assertEquals(toEdit, fromDao);
    }

    /**
     * Test of deleteSuperHeroById method, of class SuperheroDaoDB.
     */
    @Test
    public void testDeleteSuperHeroById() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        Superhero toDelete = superherodao.getSuperHeroById(superhero.getId());
        superherodao.deleteSuperHeroById(toDelete.getId());
        Superhero fromDao=superherodao.getSuperHeroById(superhero.getId());
        assertNull(fromDao);
    }
    
  
    
     /**
     * Test of getAllOrganizationBySuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void getAllOrganizationBySuperhero() {
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
        
        List<Organization> fromDao=superherodao.getAllOrganizationBySuperhero(superhero.getId());

        assertEquals(1,fromDao.size());
      //  assertTrue(fromDao.contains(sighting));
    }
    
    /**
     * Test of getAllLocationBySuperHero method, of class SuperheroDaoDB.
     */
    @Test
    public void getAllLocationBySuperHero() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);
        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization organization = new Organization();
        organization.setName("SWG");
        organization.setDesc("Software Guild");
        organization.setAddress("15 50th");
        organization.setContact("83838");
        organization.setSuperheros(superheros);
        organizationdao.addOrganization(organization);

        Location location = new Location();
        location.setName("Karmel");
        location.setDesc("karmel Mal");
        location.setAddress("Lake street");
        locationdao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDateTime.now().withNano(0));
        sighting.setLocations(location);
        sighting.setSuperheros(superheros);
        sightingdao.addSighting(sighting);

        List<Location> fromDao = superherodao.getAllLocationBySuperHero(superhero.getId());

        assertEquals(1, fromDao.size());
        //  assertTrue(fromDao.contains(sighting));
    }


}
