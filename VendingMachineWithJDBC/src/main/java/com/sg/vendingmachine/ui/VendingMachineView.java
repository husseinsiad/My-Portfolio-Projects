/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author siyaa
 */
@Component
public class VendingMachineView {

    UserIO io;
@Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

//    public Item getItemInfo() {
//        String itemName = io.readString("Enter ItemName ");
//        String itemCost = io.readString("Enter Item-Cost ");
//        int numberOfItems = io.readInt("numberOfItemsInInventory");
//        Item items = new Item();
//        items.setItemName(itemName);
//        BigDecimal cost = new BigDecimal(itemCost);
//        items.setItemCost(cost);
//        items.setNumberOfItemsInInventory(numberOfItems);
//
//        return items;
//    }

    //TODO: break into 2 methods
    public String getItemFromUser() {
        String parchasedItemId = io.readString("Select Item ");
//        Item items = new Item();
//        items.setItemid(parchasedItemId);

        return parchasedItemId;
    }

    public BigDecimal getMoneyFromUser() {
        BigDecimal depositMoney= io.readMoney("Enter Deposit Money ");
        return depositMoney;
    }



    public void displayItemList(List<Item> itemList) {
        //io.print("Item_ID   ItemName           Price  Availible Item");
        io.printf();
        io.print("________________________________________________________");
        for (Item currentItem : itemList) {
            // Display available items only
            if (currentItem.getNumberOfItemsInInventory() > 0) {
                io.printf(currentItem.getItemid(), currentItem.getItemName(),
                        currentItem.getItemCost(), currentItem.getNumberOfItemsInInventory());
                io.print("\n________________________________________________________");
            }

            //io.readString("Please hit enter to continue.");
        }
    }//end method

    public int printMenuSelectin() {
        io.print("1:Exit Item");
        io.print("2:Depost Money");
        return io.readInt("Select Your choices.", 1, 5);
    }

    //Display Messages
    public void welcomeBanner() {
        io.print("Welcome Vending Machine System");

    }

    public void displayCreateItemBanner() {
        io.print("=== Create New Item ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Item successfully created.  Please hit enter to continue");
    }

    public void displayPurchasedSuccessBanner() {
        io.readString(
                "Item successfully Purchased.  Please hit enter to continue");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Items ===");
    }

    public void displayChange(Change returnedChange) {
                io.print(" Remainig Change(Dolor): " + returnedChange.getDollar());
        io.print(" Remainig Change(Quarter): " + returnedChange.getQuarter());
        io.print(" Remainig Change(Diamond): " + returnedChange.getDime());
        io.print(" Remainig Change(Nickel): " + returnedChange.getNickel());
        io.print(" LeftOverPennies: " + returnedChange.getLeftOverPennies());
      }

    

}
