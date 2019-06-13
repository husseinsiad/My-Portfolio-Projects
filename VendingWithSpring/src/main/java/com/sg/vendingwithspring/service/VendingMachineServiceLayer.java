/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingwithspring.service;

import com.sg.vendingwithspring.dao.VendingPersistenceException;
import com.sg.vendingwithspring.dto.Change;
import com.sg.vendingwithspring.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface VendingMachineServiceLayer {
     public Item getItem(String itemid)
            throws VendingPersistenceException, NoItemInventoryException;

    public List<Item> getAllItems() 
            throws VendingPersistenceException;

    public Change vendItem(Item item, BigDecimal deposit) 
            throws VendingPersistenceException, NoItemInventoryException, InsufficientFundsException ;
}
