/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dao.orderDao;
import com.sg.flooringmastery.dao.orderDaoStubImpl;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
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
public class orderServiceTest {

    private orderService service;

    public orderServiceTest() {
        orderDao dao = new orderDaoStubImpl();
        service = new orderServiceImpl(dao);
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
     * Test of getOrdersForDate method, of class orderService.
     */
    @Test
    public void testGetOrdersForDate() throws Exception {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        Assert.assertEquals(1, service.getOrdersForDate(date).size());

    }

    /**
     * Golden Path Test of addOrder method, of class orderService.
     */
    @Test
    public void testAddOrder() throws Exception {
        //1:Arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2919", df);
        Order order = new Order();
        order.setOrderNum(2);
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
            Order test = service.addOrder(order);
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
     * Bad Path Test of addOrder method, of class orderService.
     */
    @Test
    public void testAddNullOrder() throws Exception {
        //1:Arrange
        Order order = new Order();
        order = null;

        try {
            //2:Act
            Order test = service.addOrder(order);
            //3:Assert
            Assert.fail();
        } catch (PersistenceException ex) {

        }

    }

    /**
     * Golden Path Test of editOrder method, of class orderService.
     */
    @Test
    public void testEditOrder() throws Exception {
        //1:Arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);

        List<Order> getOrderForUpdate = service.getOrdersForDate(date);
        Order toUpdate = getOrderForUpdate.stream().filter(u -> u.getOrderNum() == 1).findFirst().orElse(null);
        toUpdate.setOrderNum(1);
        toUpdate.setDate(date);
        toUpdate.setCustName("Moha");
        Product product = new Product();
        product.setproductName("Carpet");
        product.setLaborCostPerSqFoot(new BigDecimal("1.15"));
        product.setMatCostPerSqFoot(new BigDecimal("2.75"));
        toUpdate.setProdInfo(product);
        Tax taxes = new Tax();
        taxes.setstateAbbrev("IN");
        taxes.setTaxRate(new BigDecimal("3.25"));
        toUpdate.setTaxInfo(taxes);
        toUpdate.setArea(new BigDecimal("200"));

        try {
            //2:Act
            Order test = service.editOrder(toUpdate);
            //3:Assert 
            Assert.assertEquals("Moha", test.getCustName());
            Assert.assertEquals("Carpet", test.getProdInfo().getproductName());
            Assert.assertEquals("IN", test.getTaxInfo().getstateAbbrev());
            Assert.assertEquals(new BigDecimal("200"), test.getArea());

            Order validation = service.getOrdersForDate(date).stream().filter(o -> o.getOrderNum() == 1).findFirst().orElse(null);
            Assert.assertEquals("Moha", validation.getCustName());
            Assert.assertEquals("Carpet", validation.getProdInfo().getproductName());
            Assert.assertEquals("IN", validation.getTaxInfo().getstateAbbrev());
            Assert.assertEquals(new BigDecimal("200"), validation.getArea());
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
        Order toUpdate = new Order();
        toUpdate = null;
        try {
            //2:Act
            Order test = service.editOrder(toUpdate);
            //3:Assert 
//            Assert.assertEquals("John", test.getCustName());
//            Assert.assertEquals("Wood", test.getProdInfo().getproductName());
//            Assert.assertEquals("OH", test.getTaxInfo().getstateAbbrev());
//            Assert.assertEquals(new BigDecimal("100"), test.getArea());
            Assert.fail();
        } catch (PersistenceException ex) {

        }

    }

    /**
     * Golden Path Test of removeOrder method, of class orderService.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        //1:Arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        List<Order> getOrderForUpdate = service.getOrdersForDate(date);
        Order toRemove = getOrderForUpdate.stream().filter(u -> u.getOrderNum() == 1).findFirst().orElse(null);

        try {
            //2:Act
            service.removeOrder(toRemove);
            //3:Assert 
//            Assert.assertEquals("Moha", toRemove.getCustName());
//            Assert.assertEquals("Laminate", toRemove.getProdInfo().getproductName());
//            Assert.assertEquals("IN", toRemove.getTaxInfo().getstateAbbrev());
//            Assert.assertEquals(new BigDecimal("100"), toRemove.getArea());

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
        List<Order> getOrderForUpdate = service.getOrdersForDate(date);
        Order toRemove = getOrderForUpdate.stream().filter(u -> u.getOrderNum() == 1).findFirst().orElse(null);
        toRemove = null;
        try {
            //2:Act
            service.removeOrder(toRemove);
            //3:Assert 
//            Assert.assertEquals("Hussein", toRemove.getCustName());
//            Assert.assertEquals("Wood", toRemove.getProdInfo().getproductName());
//            Assert.assertEquals("OH", toRemove.getTaxInfo().getstateAbbrev());
//            Assert.assertEquals(new BigDecimal("100"), toRemove.getArea());
            Assert.fail();
        } catch (PersistenceException ex) {

        }

    }

    /**
     * Golden Path Test of getAllProduct method, of class orderService.
     */
    @Test
    public void testGetAllProduct() throws Exception {
        List<Product> allProduct = service.getAllProduct();
        Assert.assertEquals(1, allProduct.size());
    }

    /**
     * Golden Path Test of getProductByName method, of class orderService.
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
            Product test = service.getProductByName(product);
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
            Product test = service.getProductByName(product);
            //Assert
            Assert.assertEquals("Table", test.getproductName());
            Assert.assertEquals(new BigDecimal("2.15"), test.getLaborCostPerSqFoot());
            Assert.assertEquals(new BigDecimal("2.75"), test.getMatCostPerSqFoot());
            Assert.fail();
        } catch (PersistenceException ex) {

        }

    }

}
