/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author siyaa
 */
@Component
public class ItemDaoDBImpl implements ItemDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Item getItem(String itemid) throws VendingPersistenceException, NoItemInventoryException {
        System.out.println("Items");
        try {
            Item item = jdbc.queryForObject("SELECT * FROM item WHERE itemid= ?", new ItemMapper(), itemid);
            System.out.println(
                    item.getItemid()+" "+
                    item.getItemName()+" "+
                    item.getItemCost()+" "+
                    item.getNumberOfItemsInInventory());
            System.out.println("Item List Completed");
            return item;
        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Item> getAllItems() throws VendingPersistenceException {
        System.out.println("List All Items");
         List<Item>items= jdbc.query("SELECT * FROM item", new ItemMapper());
        for (Item allItem : items) {
            System.out.println(
                    allItem.getItemid()+" "+
                    allItem.getItemName()+" "+
                    allItem.getItemCost()+" "+
                    allItem.getNumberOfItemsInInventory());
        }
         System.out.println("Item List Completed");
         return items;
    }

        @Override
        public void updateItem(Item item) throws VendingPersistenceException, NoItemInventoryException {
            jdbc.update("UPDATE item set numberOfItemsInInventory= ?",item.getNumberOfItemsInInventory());
            
        }

    

    private static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();
            item.setItemid(rs.getString("itemid"));
            item.setItemName(rs.getString("itemName"));
            item.setItemCost(rs.getBigDecimal("itemCost"));
            item.setNumberOfItemsInInventory(rs.getInt("numberOfItemsInInventory"));
            return item;
        }

    }

}
