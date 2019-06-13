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
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author dsmelser
 */
public interface orderDao {
    
    public List<Order> getOrdersForDate( LocalDate date )throws PersistenceException,NotFoundException;
    public Order addOrder( Order toAdd )throws PersistenceException,NotFoundException;
    public Order editOrder( Order newData )throws PersistenceException;
    public void removeOrder(Order order)throws PersistenceException;
    public Product getProductByName(Product product)throws PersistenceException,NotFoundException;
    public List<Product>  getAllProduct()throws PersistenceException,NotFoundException;
    public Tax getTax(Tax state) throws PersistenceException, NotFoundException;
    //public Tax getStateInfo(Tax state)throws PersistenceException,NotFoundException;
    //public List<Order> getOrder( Order orderInfo )throws PersistenceException,NotFoundException;
    
}
