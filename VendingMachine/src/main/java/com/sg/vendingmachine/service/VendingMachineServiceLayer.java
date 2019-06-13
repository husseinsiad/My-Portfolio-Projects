/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
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
    
     //public void checkMoney(Item itemcost, BigDecimal deposit) throws InsufficientFundsException;
     //public void checkInventory(Item item) throws NoItemInventoryException;
}
