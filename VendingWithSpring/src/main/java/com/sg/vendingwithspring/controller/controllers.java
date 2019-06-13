/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingwithspring.controller;

import com.sg.vendingwithspring.dao.VendingPersistenceException;
import com.sg.vendingwithspring.dto.Change;
import com.sg.vendingwithspring.dto.Item;
import com.sg.vendingwithspring.service.InsufficientFundsException;
import com.sg.vendingwithspring.service.NoItemInventoryException;
import com.sg.vendingwithspring.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author siyaa
 */
@RestController
@RequestMapping("/api")

public class controllers {
    @Autowired 
    VendingMachineServiceLayer service;
    @GetMapping("/")
    public String[] helloWorld() {
        String[] result = {"Hello", "World", "!"};
        return result;
    }
    
     @GetMapping("/addItem")
    public Item addItem(){
        Item item=new Item();
        item.setItemid("1");
        item.setItemName("Fanta");
        item.setItemCost(new BigDecimal("2.6"));
        item.setNumberOfItemsInInventory(3);
        return item;
        
    }
    
      @GetMapping("/getAllItem")
    public List<Item> getAllItem() throws VendingPersistenceException{
         List<Item> items= service.getAllItems(); 
         return items;
    }
    
      @GetMapping("/getItem")
    public Item getItem(String itemid) throws VendingPersistenceException, NoItemInventoryException{    
        Item items= service.getItem(itemid); 
         return items;
    }
    
        @PostMapping("/vendItem")
    public Change vendItem(String itemid,BigDecimal Deposit) throws VendingPersistenceException, NoItemInventoryException, InsufficientFundsException{    
       try{
        Item item=service.getItem(itemid);
        Change money= service.vendItem(item, Deposit); 
         return money;
       } catch(NoItemInventoryException ex){
           throw new NoItemInventoryException(ex.getMessage());
       }
       
    }
    
    
}
