/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author siyaa
 */
//@Component
public class ItemDaoImpl implements ItemDao {

    public static final String ITEMS_FILE = "items.txt";
    public static final String DELIMITER = "::";
    private Map<String, Item> items = new HashMap<>();

    

    @Override
    public Item getItem(String itemid) throws VendingPersistenceException, NoItemInventoryException {
        loadItems();
        Item newItem = items.get(itemid);
        if (newItem == null) {
            throw new NoItemInventoryException(" the itemid doesn't exist");
        }

        //List<Item> returnitem;
        if (itemid.equals(newItem.getItemid())) {
            return items.get(itemid);
        } else {
            return null;
        }

    }

    @Override
    public List<Item> getAllItems() throws VendingPersistenceException {
        loadItems();
        return new ArrayList<Item>(items.values());
    }

    @Override
    public void updateItem(Item item) throws VendingPersistenceException, NoItemInventoryException{
        loadItems();
        if (item.getNumberOfItemsInInventory() < 0) {
            throw new NoItemInventoryException("This Item Is Not Available In The Store");
        } else {
            items.put(item.getItemid(), item);
             writeItem();
        }

    }

    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        Item itemFromFile = new Item();
        // Index 0 -Itemid
        itemFromFile.setItemid(itemTokens[0]);
        // Index 1 -ItemName
        itemFromFile.setItemName(itemTokens[1]);
        // Index 2 - ItemCost
        BigDecimal itemCost = new BigDecimal(itemTokens[2]);
        itemFromFile.setItemCost(itemCost);
        // Index 3 - setNumberOfItemsInInventory
        int numberOfItem = Integer.parseInt(itemTokens[3]);
        itemFromFile.setNumberOfItemsInInventory(numberOfItem);
        return itemFromFile;
    }

    private void loadItems() throws VendingPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingPersistenceException(
                    "-_- Could not load items data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItems holds the most recent item unmarshalled
        Item currentitem;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentitem = unmarshallItem(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            items.put(currentitem.getItemid(), currentitem);
        }
        // close scanner
        scanner.close();
    }

    private String marshallItem(Item item) {
        // ItemID
        String itemAsText = item.getItemid() + DELIMITER;
        // ItemName
        itemAsText += item.getItemName() + DELIMITER;
        // ItemCost
        itemAsText += item.getItemCost() + DELIMITER;
        // NumberOfItemsInInventory
        itemAsText += item.getNumberOfItemsInInventory();
        return itemAsText;
    }

    private void writeItem() throws VendingPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingPersistenceException(
                    "Could not save item data.", e);
        }

        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            // turn a item into a String
            itemAsText = marshallItem(currentItem);
            // write the Item object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
