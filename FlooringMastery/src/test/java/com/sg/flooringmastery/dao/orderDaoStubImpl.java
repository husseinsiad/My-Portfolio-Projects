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
import com.sg.flooringmastery.service.orderService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author siyaa
 */
public class orderDaoStubImpl implements orderDao {

    List<Order> orderList = new ArrayList<>();
    List<Product> productList=new ArrayList<>();

    public orderDaoStubImpl() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse("06-02-2019", df);
        Order order = new Order();
        order.setOrderNum(1);
        order.setDate(date);
        order.setCustName("Hussein");

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
        orderList.add(order);
        
        
       Product onlyproduct = new Product();
        onlyproduct.setproductName("Wood");
        onlyproduct.setLaborCostPerSqFoot(new BigDecimal("5.15"));
        onlyproduct.setMatCostPerSqFoot(new BigDecimal("4.75"));
        productList.add(onlyproduct);

    }
        
    

    @Override
    public List<Order> getOrdersForDate(LocalDate date) throws PersistenceException, NotFoundException {
        List<Order> orderForDate = new ArrayList<>();
        if (date != null) {
            orderForDate = orderList.stream().collect(Collectors.toList());
            return orderForDate;
        } else {
            throw new PersistenceException("Could not find Order");
        }
    }

    @Override
    public Order addOrder(Order toAdd) throws PersistenceException, NotFoundException {
        if (toAdd == null) {
            throw new PersistenceException("Tried to Add null order");
        }
        orderList.add(toAdd);
        return toAdd;
    }

    @Override
    public Order editOrder(Order newData) throws PersistenceException {

        if (newData == null) {
            throw new PersistenceException("Tried to update null Order.");
        }
        List<Order> orders = orderList;
        int matchedIndex = -1;
        for (int i = 0; i < orders.size(); i++) {
            Order toCheck = orders.get(i);
            if (toCheck.getOrderNum() == (newData.getOrderNum())) {
                matchedIndex = i;
                break;
            }
        }
        orders.remove(matchedIndex);
        orderList.add(newData);

        return newData;
    }

    @Override
    public void removeOrder(Order order) throws PersistenceException {
        if (order == null) {
            throw new PersistenceException("Tried to Remove null order");
        }
        List<Order> ordersForDay = orderList;
        //find order that matches and remove at that position in the list
        int matchingIndex = -1;
        for (int i = 0; i < ordersForDay.size(); i++) {
            Order toCheck = ordersForDay.get(i);
            if (toCheck.getOrderNum() == (order.getOrderNum())) {
                matchingIndex = i;
                break;
            }
        }
        ordersForDay.remove(matchingIndex);
        //call the write 
        //orderList.add(ordersForDay);
    }

    @Override
    public Product getProductByName(Product product) throws PersistenceException, NotFoundException {
//        orderList
//                .stream()
//                .filter(p -> p.getProdInfo().getproductName().equalsIgnoreCase(product.getproductName()))
//                .findFirst().orElse(null);
        for(Order currentProduct:orderList){
            if(currentProduct.getProdInfo().getproductName().equalsIgnoreCase(product.getproductName()))
            {
                return product;   
            }
            else{
            throw new PersistenceException("Product Type was not Found");
            }
        }
 
      return product;  
    }

    @Override
    public List<Product> getAllProduct() throws PersistenceException, NotFoundException {
                return productList;
    }

   

    @Override
    public Tax getTax(Tax state) throws PersistenceException, NotFoundException {
        orderList.stream().filter(t -> t.getTaxInfo().equals(state)).findFirst().orElse(null);
        if (state != null) {
            return state;
        } else {
            throw new NotFoundException("State Abbreviations was not Found");
        }
    }

}
