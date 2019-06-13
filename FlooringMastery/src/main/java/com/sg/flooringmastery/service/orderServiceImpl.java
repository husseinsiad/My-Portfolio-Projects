/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dao.orderDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author siyaa
 */
public class orderServiceImpl implements orderService {

    orderDao dao;

    public orderServiceImpl(orderDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Order> getOrdersForDate(LocalDate date) throws PersistenceException, NotFoundException {
        if(date !=null){
        return dao.getOrdersForDate(date);
        }
        else
            throw new PersistenceException("Could not find Order");
        
    }

    @Override
    public Product getProductByName(Product product) throws PersistenceException, NotFoundException {
        Product returnProduct = dao.getProductByName(product);
        if (returnProduct != null) {
            return returnProduct;
        } else {
            throw new NotFoundException("This Product Type was not Found");
        }
    }
    @Override
    public Tax getTax(Tax state) throws PersistenceException, NotFoundException {
        Tax returnState = dao.getTax(state);
        if (returnState != null) {
            return returnState;
        } else {
            throw new NotFoundException("State Abbreviations was not Found");
        }
    }

    @Override
    public List<Product> getAllProduct() throws PersistenceException, NotFoundException {
        return dao.getAllProduct();
    }

    @Override
    public Order addOrder(Order toAdd) throws PersistenceException, NotFoundException {
        if (toAdd == null) {
            throw new PersistenceException("Tried to Add null order");
        }
        LocalDate todayDate=LocalDate.now();
        if (toAdd.getDate().isBefore(todayDate)) {
         throw new PersistenceException("The orderDate should be Today's date or Future Date");
        }
        Product productInfo = getProductByName(toAdd.getProdInfo());
        Tax taxInfo = getTax(toAdd.getTaxInfo());
        toAdd.setCustName(toAdd.getCustName());
        toAdd.setTaxInfo(taxInfo);
        toAdd.setProdInfo(productInfo);
        toAdd.setArea(toAdd.getArea());
        return dao.addOrder(toAdd);

    }

    @Override
    public Order editOrder(Order newData) throws PersistenceException, NotFoundException {

            if (newData == null) {
                throw new PersistenceException("Tried to Edit null order");
            }
            Product productInfo = getProductByName(newData.getProdInfo());
            Tax taxInfo = getTax(newData.getTaxInfo());
            newData.setCustName(newData.getCustName());
            newData.setTaxInfo(taxInfo);
            newData.setProdInfo(productInfo);
            newData.setArea(newData.getArea());
            
        return dao.editOrder(newData);
    }

    @Override
    public void removeOrder(Order order) throws PersistenceException {
        if (order == null) {
            throw new PersistenceException("Tried to Remove null order");
        }
        dao.removeOrder(order);
    }

    @Override
    public List<Order> getOrders(LocalDate date) throws PersistenceException, NotFoundException {
        if (date == null) {
            throw new NotFoundException("Could not find order");
        }
        return dao.getOrdersForDate(date);
    }
//
}
