/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingwithspring.dao;

import com.sg.vendingwithspring.dto.Item;
import com.sg.vendingwithspring.service.NoItemInventoryException;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface ItemDao {
    public Item addItem(String itemid, Item item)throws VendingPersistenceException;
    public Item getItem(String itemid)throws VendingPersistenceException,NoItemInventoryException;
    public List<Item> getAllItems()throws VendingPersistenceException;
    public void updateItem(Item item)throws VendingPersistenceException,NoItemInventoryException;
}
