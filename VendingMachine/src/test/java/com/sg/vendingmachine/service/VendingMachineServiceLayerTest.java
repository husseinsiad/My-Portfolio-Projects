/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.AuditLogDao;
import com.sg.vendingmachine.dao.AuditLogDaoStubImp;
import com.sg.vendingmachine.dao.ItemDao;
import com.sg.vendingmachine.dao.ItemDaoStubImpl;
import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
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
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        ItemDao dao = new ItemDaoStubImpl();
        AuditLogDao AuditDao = new AuditLogDaoStubImp();
        service = new VendingMachineServiceLayerImpl(dao, AuditDao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testAddItem() throws Exception {
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(1, service.getAllItems().size());
    }

    //Goldan Path
    // Goood Input
    // Good OutPut
    @Test
    public void testUpdatevalidItem() throws Exception {
        Item item = new Item();
        //Item vendItem= service.getItem("1");
        item.setItemid("1");
        item.setItemName("DXN");
        item.setItemCost(new BigDecimal("2.5"));
        item.setNumberOfItemsInInventory(15);

        try {
            Change test=service.vendItem(item,new BigDecimal("3"));
            Assert.assertEquals(2, test.getQuarter());
            Assert.assertEquals(0, test.getDime());
            Assert.assertEquals(0, test.getNickel());
            Assert.assertEquals(0, test.getLeftOverPennies());
            Assert.assertEquals(0, test.getDollar());
             Item validation=service.getItem("1");
             Assert.assertEquals(14, validation.getNumberOfItemsInInventory());
            
        } catch (NoItemInventoryException ex) {
            fail();
        }
    }

    // Bad Input
    @Test
    public void testUpdateInvalidItem() throws Exception {
        Item item = new Item();
        item.setItemid("99");
        item.setItemName("DXN");
        item.setItemCost(new BigDecimal("2.5"));
        item.setNumberOfItemsInInventory(15);

        try {
            service.vendItem(item, new BigDecimal(3));
            fail();
        } catch (NoItemInventoryException ex) {

        }
    }

    @Test
    public void testGetItemInvalidId() throws VendingPersistenceException {

        try {
            Item test = service.getItem("0");
            assertEquals("0", test.getItemid());
            fail();
        } catch (NoItemInventoryException ex) {

        }
    }

    @Test
    public void testGetItemValidId() {

        try {
            Item test = service.getItem("1");

            assertEquals("1", test.getItemid());

        } catch (VendingPersistenceException | NoItemInventoryException ex) {
            fail();
        }
    }

//    /**
//     * Test of checkInventory method, of class VendingMachineServiceLayer.
//     */
//    //golden path
//    //will send and item with >0 inventory
//    @Test
//    public void testCheckInventory() {
//
//        Item item = new Item();
//        item.setNumberOfItemsInInventory(1);
//
//        try {
//            service.checkInventory(item);
//        } catch (NoItemInventoryException ex) {
//
//            fail();
//        }
//
//    }
//
//    // Bad Input
//    @Test
//    public void testCheckInventoryZeroQty() {
//
//        Item testItm = new Item();
//        testItm.setNumberOfItemsInInventory(0);
//
//        try {
//            service.checkInventory(testItm);
//            fail();
//        } catch (NoItemInventoryException ex) {
//
//        }
//    }
}
