/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.service.NotFoundException;
import com.sg.flooringmastery.service.orderService;
import com.sg.flooringmastery.ui.flooringView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author siyaa
 */
public class flooringController {

    flooringView view;
    orderService service;
    boolean hasError = false;

    public flooringController(flooringView view, orderService service) {
        this.view = view;
        this.service = service;
    }

    public int getMenuSelection() {
        return view.getMenu();
    }

    private void displayOrder() throws PersistenceException, NotFoundException {
        Order order = view.getOrderForDate();
        do {
            try {
                List<Order> currentOrder = service.getOrdersForDate(order.getDate());
                view.displayOrderInfo(currentOrder);
                hasError = false;
            } catch (PersistenceException | NotFoundException ex) {
                view.displayErrorMessage(ex.getMessage());
                hasError = true;
            }
        } while (hasError);

    }

    private void addOrder() throws PersistenceException, NotFoundException {
        List<Product> currentProduct = service.getAllProduct();
        //Display all products on the screen
        //Before user make order
        view.displayProductInfo(currentProduct);
        do {
            try {

                //Display User Order
                Order userOrder = view.addOrderInfo();
                view.displayOrderForAdd(userOrder);
                String confirmation = view.orderConfirmation();
                if (confirmation.equalsIgnoreCase("y")) {
                    if(userOrder.getArea().compareTo(BigDecimal.ZERO) < 0) {
                        hasError=true;
                    throw new NotFoundException("Invalid Area");
                    }
                    service.addOrder(userOrder);
                    hasError = false;
                } else {
                    getMenuSelection();
                }
            } catch (PersistenceException | NotFoundException ex) {
                view.displayErrorMessage(ex.getMessage());
                hasError = true;
            }

        } while (hasError);

    }

    private void editOrder() throws PersistenceException, NotFoundException {

        do {
            try {
                Order orderInfo = view.getOrderInfo();
                List<Order> orderForEdit = service.getOrders(orderInfo.getDate());
                Order toEdit = orderForEdit.stream().filter(o -> o.getOrderNum() == orderInfo.getOrderNum()).findFirst().orElse(null);

                if (toEdit != null) {
                    Order currentOrder = view.editOrder(toEdit);
                    service.editOrder(currentOrder);
                    hasError = false;
                } else {
                    hasError = true;
                    throw new NotFoundException("Order Number: " + orderInfo.getOrderNum() + " could not find");
                }
            } catch (PersistenceException | NotFoundException ex) {
                view.displayErrorMessage(ex.getMessage());
                hasError = true;

            }

        } while (hasError);

    }

    private void removeOrder() throws PersistenceException, NotFoundException {
        do {

            try {
                Order orderInfo = view.getOrderInfo();
                List<Order> OrderForRemove = service.getOrders(orderInfo.getDate());
                List<Order> orderList = new ArrayList<>();
                Order toRemove = OrderForRemove.stream().filter(o -> o.getOrderNum() == orderInfo.getOrderNum()).findFirst().orElse(null);
                orderList.add(toRemove);
                view.displayOrderForRemoveInfo(orderList);
                String confirmation = view.removeConfirmation();
                if (confirmation.equalsIgnoreCase("y")) {
                    service.removeOrder(orderInfo);
                    hasError = false;
                } else {
                    getMenuSelection();
                }
            } catch (PersistenceException | NotFoundException ex) {
                view.displayErrorMessage(ex.getMessage());
                hasError = true;
            }
        } while (hasError);

    }

    private void exitProgram() {
        view.exitMode();
    }

    public void run() throws PersistenceException, NotFoundException {
        int userChoice = 0;
        boolean keepGoing = true;

        while (keepGoing) {
            userChoice = getMenuSelection();
            switch (userChoice) {
                case 1:
                    displayOrder();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exitProgram();
                    keepGoing = false;
                    break;

            }

        }
    }

}
