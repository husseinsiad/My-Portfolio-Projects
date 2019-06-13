/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface orderService {

    public List<Order> getOrdersForDate(LocalDate date)throws PersistenceException,NotFoundException;

    public Order addOrder(Order toAdd)throws PersistenceException,NotFoundException;

    public Order editOrder(Order newData) throws PersistenceException, NotFoundException;

    public void removeOrder(Order order)throws PersistenceException;

    public List<Product> getAllProduct()throws PersistenceException,NotFoundException;
   public Tax getTax(Tax state) throws PersistenceException, NotFoundException;
    public Product getProductByName(Product product)throws PersistenceException, NotFoundException;
     public List<Order> getOrders( LocalDate date )throws PersistenceException,NotFoundException;
}
