/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.NotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author siyaa
 */
public class orderDaoTest {

    orderDao dao = new orderDaoImpl(Paths.get("data", "test").toString());

    public orderDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        Path testPath = Paths.get("data", "test");
        Path seedPath = Paths.get("data", "seed");

        File testFolder = new File(testPath.toUri());
        File seedFolder = new File(seedPath.toUri());

        File[] testFiles = testFolder.listFiles();

        for (File toDelete : testFiles) {
            toDelete.delete();
        }

        File[] seedFiles = seedFolder.listFiles();
        for (File toCopy : seedFiles) {
            Files.copy(toCopy.toPath(), Paths.get(testFolder.toString(), toCopy.getName()), StandardCopyOption.REPLACE_EXISTING);
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Golden Path Test of getOrdersForDate method, of class orderDao.
     */
    @Test
    public void testGetOrdersForDate() throws Exception {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        Assert.assertEquals(4, dao.getOrdersForDate(date).size());
    }

    /**
     * Golden Path Test of addOrder method, of class orderDao.
     */
    @Test
    public void testAddOrder() throws Exception {
        //1:Arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        Order order = new Order();
        order.setDate(date);
        order.setCustName("John");

        Product product = new Product();
        product.setproductName("Wood");
        product.setLaborCostPerSqFoot(new BigDecimal("5.15"));
        product.setMatCostPerSqFoot(new BigDecimal("4.75"));
        order.setProdInfo(product);
        Tax taxes = new Tax();
        taxes.setstateAbbrev("OH");
        taxes.setTaxRate(new BigDecimal("6.25"));
        order.setTaxInfo(taxes);
        order.setArea(new BigDecimal("100"));

        try {
            //2:Act
            Order test = dao.addOrder(order);
            //3:Assert
            Assert.assertEquals("John", test.getCustName());
            Assert.assertEquals("Wood", test.getProdInfo().getproductName());
            Assert.assertEquals("OH", test.getTaxInfo().getstateAbbrev());
            Assert.assertEquals(new BigDecimal("100"), test.getArea());
        } catch (PersistenceException ex) {
            Assert.fail();
        }

    }

    /**
     * Bad Path Test of addOrder method, of class orderDao.
     */
    @Test
    public void testAddNullOrder() throws Exception {
        //1:Arrange
        Order order = new Order();
        order = null;

        try {
            //2:Act
            Order test = dao.addOrder(order);
            //3:Assert
            Assert.fail();
        } catch (PersistenceException ex) {

        }

    }

    /**
     * Golden Path Test of editOrder method, of class orderDao.
     */
    @Test
    public void testEditOrder() throws Exception {
        //1:Arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        List<Order> getOrderForUpdate = dao.getOrdersForDate(date);
        Order toUpdate = getOrderForUpdate.stream().filter(u -> u.getOrderNum() == 1).findFirst().orElse(null);

        toUpdate.setCustName("John");
        Product product = new Product();
        product.setproductName("Wood");
        product.setLaborCostPerSqFoot(new BigDecimal("5.15"));
        product.setMatCostPerSqFoot(new BigDecimal("4.75"));
        toUpdate.setProdInfo(product);
        Tax taxes = new Tax();
        taxes.setstateAbbrev("OH");
        taxes.setTaxRate(new BigDecimal("6.25"));
        toUpdate.setTaxInfo(taxes);
        toUpdate.setArea(new BigDecimal("100"));

        try {
            //2:Act
            Order test = dao.editOrder(toUpdate);
            //3:Assert 
            Assert.assertEquals("John", test.getCustName());
            Assert.assertEquals("Wood", test.getProdInfo().getproductName());
            Assert.assertEquals("OH", test.getTaxInfo().getstateAbbrev());
            Assert.assertEquals(new BigDecimal("100"), test.getArea());
        } catch (PersistenceException ex) {
            Assert.fail();
        }

    }

    /**
     * Bad Path Test of editOrder method, of class orderDao.
     */
    @Test
    public void testEditNullOrder() throws Exception {
        //1:Arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        List<Order> getOrderForUpdate = dao.getOrdersForDate(date);
        Order toUpdate = getOrderForUpdate.stream().filter(u -> u.getOrderNum() == 1).findFirst().orElse(null);

        toUpdate = null;
        try {
            //2:Act
            Order test = dao.editOrder(toUpdate);
            //3:Assert 
            Assert.assertEquals("John", test.getCustName());
            Assert.assertEquals("Wood", test.getProdInfo().getproductName());
            Assert.assertEquals("OH", test.getTaxInfo().getstateAbbrev());
            Assert.assertEquals(new BigDecimal("100"), test.getArea());
            Assert.fail();
        } catch (PersistenceException ex) {

        }

    }

    /**
     * Golden Path Test of removeOrder method, of class orderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        //1:Arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        List<Order> getOrderForUpdate = dao.getOrdersForDate(date);
        Order toRemove = getOrderForUpdate.stream().filter(u -> u.getOrderNum() == 1).findFirst().orElse(null);

        try {
            //2:Act
            dao.removeOrder(toRemove);
            //3:Assert 
            Assert.assertEquals("Moha", toRemove.getCustName());
            Assert.assertEquals("Laminate", toRemove.getProdInfo().getproductName());
            Assert.assertEquals("IN", toRemove.getTaxInfo().getstateAbbrev());
            Assert.assertEquals(new BigDecimal("100"), toRemove.getArea());

        } catch (PersistenceException ex) {
            Assert.fail();
        }

    }

    /**
     * Bad Path Test of removeOrder method, of class orderDao.
     */
    @Test
    public void testRemoveNullOrder() throws Exception {
        //1:Arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        List<Order> getOrderForUpdate = dao.getOrdersForDate(date);
        Order toRemove = getOrderForUpdate.stream().filter(u -> u.getOrderNum() == 1).findFirst().orElse(null);
        toRemove = null;
        try {
            //2:Act
            dao.removeOrder(toRemove);
            //3:Assert 
//            Assert.assertEquals("Moha", toRemove.getCustName());
//            Assert.assertEquals("Laminate", toRemove.getProdInfo().getproductName());
//            Assert.assertEquals("IN", toRemove.getTaxInfo().getstateAbbrev());
//            Assert.assertEquals(new BigDecimal("100"), toRemove.getArea());
            Assert.fail();
        } catch (PersistenceException ex) {

        }

    }

    /**
     * Golden Path Test of getProductByName method, of class orderDao.
     */
    @Test
    public void testGetProductByName() throws Exception {
        //Arrange
        Product product = new Product();
        product.setproductName("Wood");
        product.setLaborCostPerSqFoot(new BigDecimal("5.15"));
        product.setMatCostPerSqFoot(new BigDecimal("4.75"));
        //Act
        try {
            Product test = dao.getProductByName(product);
            //Assert
            Assert.assertEquals("Wood", test.getproductName());
            Assert.assertEquals(new BigDecimal("5.15"), test.getLaborCostPerSqFoot());
            Assert.assertEquals(new BigDecimal("4.75"), test.getMatCostPerSqFoot());
        } catch (PersistenceException ex) {
            Assert.fail();
        }

    }

    /**
     * Bad Path Test of getProductByName method, of class orderDao.
     */
    @Test
    public void testGetInvalidProductByName() throws Exception {
        //Arrange
        Product product = new Product();
        product.setproductName("Table");
        product.setLaborCostPerSqFoot(new BigDecimal("2.15"));
        product.setMatCostPerSqFoot(new BigDecimal("2.75"));
        //Act
        try {
            Product test = dao.getProductByName(product);
            //Assert
//            Assert.assertEquals("Table", test.getproductName());
//            Assert.assertEquals(new BigDecimal("2.15"), test.getLaborCostPerSqFoot());
//            Assert.assertEquals(new BigDecimal("2.75"), test.getMatCostPerSqFoot());
            Assert.fail();
        } catch (PersistenceException ex) {

        }

    }

    /**
     * Test of getAllProduct method, of class orderDao.
     */
    @Test
    public void testGetAllProduct() throws Exception {
        List<Product> allProduct = dao.getAllProduct();
        Assert.assertEquals(4, allProduct.size());
    }

    /**
     * Golden Path Test of getStateInfo method, of class orderDao.
     */
    @Test
    public void testGetStateInfo() throws Exception {
        Tax taxes = new Tax();
        taxes.setstateAbbrev("OH");
        taxes.setTaxRate(new BigDecimal("6.25"));
        try {
            Tax test = dao.getTax(taxes);
            Assert.assertEquals("OH", test.getstateAbbrev());
            Assert.assertEquals(new BigDecimal("6.25"), test.getTaxRate());
        } catch (PersistenceException ex) {
            Assert.fail();
        }

    }

    /**
     * Bad Path Test of getStateInfo method, of class orderDao.
     */
    @Test
    public void testGetInvalidStateInfo() throws Exception {
        Tax taxes = new Tax();
        taxes.setstateAbbrev("MN");
        taxes.setTaxRate(new BigDecimal("1.25"));
        try {
            Tax test = dao.getTax(taxes);
//            Assert.assertEquals("MN", test.getstateAbbrev());
//            Assert.assertEquals(new BigDecimal("1.25"), test.getTaxRate());
            Assert.fail();
        } catch (NotFoundException ex) {

        }

    }



}
