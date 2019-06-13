/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author siyaa
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;
    VendingMachineServiceLayerImpl serviceImpl;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            //listAllItems();
            while (keepGoing) {
                listAllItems();
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        keepGoing = false;
                        break;
                    // createItem();
                    case 2:
                        purchaseItem();
                        break;

                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void purchaseItem() {
        // view.displayDisplayStudentBanner();
        boolean hasErrors = false;
        do {

            BigDecimal userMoney = view.getMoneyFromUser();
            String itemId = view.getItemFromUser();
            try {
                Item item = service.getItem(itemId);
                //service.checkMoney(item, userMoney);
                //service.checkInventory(item);

                Change returnedChange = service.vendItem(item, userMoney);
                view.displayChange(returnedChange);
                view.displayPurchasedSuccessBanner();
                hasErrors=false;
                //listAllItems();
            }//end try
            catch (VendingPersistenceException | NoItemInventoryException | InsufficientFundsException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }//end catch

        } while (hasErrors);
    }//end method

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void listAllItems() throws VendingPersistenceException {
        view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }

    private int getMenuSelection() {
        return view.printMenuSelectin();
    }
}
