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
public class OrganizationDaoDBTest {

    @Autowired
    LocationDao locationdao;
    @Autowired
    SuperheroDao superherodao;
    @Autowired
    OrganizationDao organizationdao;
    @Autowired
    SightingDao sightingdao;

    public OrganizationDaoDBTest() {
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
     * Test of getAllOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganization() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization organization = new Organization();
        organization.setName("KC");
        organization.setDesc("Entertainment");
        organization.setAddress("Mineapolis");
        organization.setContact("84488");
        organization.setSuperheros(superheros);

        organizationdao.addOrganization(organization);
        List<Organization> fromDao = organizationdao.getAllOrganization();
        assertEquals(1, fromDao.size());

    }

    /**
     * Test of testGetAddOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAddOrganization() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization organization = new Organization();
        organization.setName("KC");
        organization.setDesc("Entertainment");
        organization.setAddress("Mineapolis");
        organization.setContact("84488");
        organization.setSuperheros(superheros);

        organizationdao.addOrganization(organization);
        Organization fromDao = organizationdao.getOrganizationById(organization.getId());
        assertEquals(fromDao, organization);
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization organization = new Organization();
        organization.setName("KC");
        organization.setDesc("Entertainment");
        organization.setAddress("Mineapolis");
        organization.setContact("84488");
        organization.setSuperheros(superheros);

        Organization fromDao = organizationdao.addOrganization(organization);
        Organization toEdit = organizationdao.getOrganizationById(organization.getId());
        toEdit.setName("KC updated");
        toEdit.setDesc("Music");
        toEdit.setAddress("Saint Cloud");
        toEdit.setContact("87229");
        toEdit.setSuperheros(superheros);
        organizationdao.updateOrganization(toEdit);

        assertNotEquals(fromDao, toEdit);
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
        Superhero superhero = new Superhero();
        superhero.setName("SuperMan");
        superhero.setDesc("a comic book");
        superhero.setSuperPower("fictional superhe");
        superhero = superherodao.addSuperHero(superhero);

        List<Superhero> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization organization = new Organization();
        organization.setName("KC");
        organization.setDesc("Entertainment");
        organization.setAddress("Mineapolis");
        organization.setContact("84488");
        organization.setSuperheros(superheros);
        //Add
        organizationdao.addOrganization(organization);
        //Get Data for Delete
        Organization toDelete = organizationdao.getOrganizationById(organization.getId());
        //Delete Data  
        organizationdao.deleteOrganizationById(toDelete.getId());
        Organization fromDao = organizationdao.getOrganizationById(organization.getId());

        assertNull(fromDao);

    }
    
    
    
    /**
     * Test of getAllSuperHeroByOrganization method, of class SuperheroDaoDB.
     */
    @Test
    public void getAllSuperHeroByOrganization() {
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
        
        List<Superhero> fromDao=organizationdao.getAllSuperHeroByOrganization(organization.getId());
        
        assertEquals(1,fromDao.size());
        assertTrue(fromDao.contains(superhero));
    }

}
