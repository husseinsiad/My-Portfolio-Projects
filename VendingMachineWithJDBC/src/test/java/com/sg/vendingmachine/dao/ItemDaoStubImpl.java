/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author siyaa
 */
public class ItemDaoStubImpl implements ItemDao {

    List<Item> itemList = new ArrayList<>();

    public ItemDaoStubImpl() {
        Item onlyItem = new Item();

        onlyItem.setItemid("1");
        onlyItem.setItemName("Fanta");
        onlyItem.setItemCost(new BigDecimal(2));
        onlyItem.setNumberOfItemsInInventory(10);
        itemList.add(onlyItem);
    }

  

    @Override
    public Item getItem(String itemid) throws VendingPersistenceException {
        if (itemid.equals(itemList.get(0).getItemid())) {
            return itemList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() throws VendingPersistenceException {
        return itemList;
    }

    @Override
    public void updateItem(Item item) throws VendingPersistenceException {
        // not implement
        if(item!=null){
          int matchingIndex = -1;
        for (int i = 0; i < itemList.size(); i++) {
            Item toCheck = itemList.get(i);
            if (toCheck.getItemid().equals(item.getItemid())) {
                matchingIndex = i;
                break;
            }
        }
        itemList.remove(matchingIndex);
        itemList.add(item);
        }
        else{
                throw new VendingPersistenceException("Can not Update Null Item"); 
                }
        
    }

    public void checkInventory(Item item) throws NoItemInventoryException {
        if (item.getNumberOfItemsInInventory() <= 0) {
            throw new NoItemInventoryException("This Item Is Not Available In The Store");
        }
    }

}
