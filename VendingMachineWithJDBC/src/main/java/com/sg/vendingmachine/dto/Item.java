/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.itemid);
        hash = 73 * hash + Objects.hashCode(this.itemName);
        hash = 73 * hash + Objects.hashCode(this.itemCost);
        hash = 73 * hash + this.numberOfItemsInInventory;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.itemid, other.itemid)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemCost, other.itemCost)) {
            return false;
        }
        return true;
    }

}
