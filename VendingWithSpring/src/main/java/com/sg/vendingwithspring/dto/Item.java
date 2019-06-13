/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingwithspring.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author siyaa
 */
public class Item {
    private String itemid;
    private String itemName;
    private BigDecimal itemCost;
    private int numberOfItemsInInventory;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public int getNumberOfItemsInInventory() {
        return numberOfItemsInInventory;
    }

    public void setNumberOfItemsInInventory(int numberOfItemsInInventory) {
        this.numberOfItemsInInventory = numberOfItemsInInventory;
    }


}
