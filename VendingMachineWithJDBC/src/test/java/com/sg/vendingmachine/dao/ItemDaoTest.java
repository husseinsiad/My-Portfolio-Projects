/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author siyaa
 */
public class ItemDaoTest {

    ItemDao dao = new ItemDaoImpl();

    public ItemDaoTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
// i don't have remove option in order to make sure 
        // that the system should be GOOD STATE
    }

    @After
    public void tearDown() {
    }



    /**
     * Test of getItem method, of class ItemDao.
     */
    @Test
    public void testGetValidItem() throws Exception {
        Item item = dao.getItem("1");
        try {
            Item test = dao.getItem(item.getItemid());
        } catch (NoItemInventoryException ex) {
            fail("Valid item id not found");
        }

    }

    @Test
    public void testGetInvalidItem() throws VendingPersistenceException {

        try {
            Item item = dao.getItem("99");
            //assertEquals( "99", item.getItemid() );
               
        } catch (NoItemInventoryException ex) {

        }

    }

    /**
     * Test of getAllItems method, of class ItemDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(5, dao.getAllItems().size());

    }

    /**
     * Test of updateItem method, of class ItemDao.
     */
    @Test
    public void testUpdateNegativeQtyItem() throws VendingPersistenceException, NoItemInventoryException {
        Item item = new Item();
        Item toUpdate = dao.getItem("2");
        toUpdate.setNumberOfItemsInInventory(-1);
        try {
            dao.updateItem(toUpdate);
            fail();
        } catch (NoItemInventoryException e) {

        }
    }

    @Test
    public void testUpdateNonZeroQtyItem() throws VendingPersistenceException, NoItemInventoryException {
        Item toUpdate = dao.getItem("1");
         toUpdate.setItemName("Fanta");
         toUpdate.setItemCost(new BigDecimal("4.87"));
         toUpdate.setNumberOfItemsInInventory(20);
        try {
            dao.updateItem(toUpdate);
            
            Item test=dao.getItem("1");
            Assert.assertEquals("Fanta", test.getItemName());
            Assert.assertEquals(new BigDecimal("4.87"), test.getItemCost());
            Assert.assertEquals(20, test.getNumberOfItemsInInventory());
            
        } catch (VendingPersistenceException e) {
           fail();
        }
    }

}
