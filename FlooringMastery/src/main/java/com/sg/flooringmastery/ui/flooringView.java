/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author siyaa
 */
public class flooringView {

    UserIO io;

    public flooringView(UserIO io) {
        this.io = io;
    }

    // GET ORDER FROM USER
    public Order addOrderInfo() {
        Order order = new Order();
        order.setDate(io.readDate("Enter OrderDate(MM-dd-yyyy)"));
        order.setCustName(io.readString("Enter Customer Name:"));
        Product prodInfo = new Product();
        prodInfo.setproductName(io.readString("Enter Product Type:"));
        Tax taxInfo = new Tax();
        taxInfo.setstateAbbrev(io.readString("Enter State(ex.MN):"));
        order.setProdInfo(prodInfo);
        order.setTaxInfo(taxInfo);
        order.setArea(io.readMoney("Enter Area:"));
        return order;
    }

    //GET ORDER FROM USER, AND DISPLAY TO THE SCREAN
    public Order getOrderByDate() {
        LocalDate date = io.readDate("Enter OrderDate(MM-dd-yyyy)");
        Order order = new Order();
        order.setDate(date);
        return order;
    }

    public void displayOrderInfo(List<Order> orderInfo) {
        io.print("* * * * * * * * * * * All Orders * * * * * * * * * * * * * * * * * * * *");
        for (Order currentOrder : orderInfo) {
            io.print("_________________________________________________________________________");
            io.print(currentOrder.getOrderNum() + " "+currentOrder.getDate() + " " + currentOrder.getCustName() + " " + currentOrder.getTaxInfo().getstateAbbrev()
                    + " " + currentOrder.getTaxInfo().getTaxRate() + " " + currentOrder.getProdInfo().getproductName()
                    + " " + currentOrder.getArea() + " " + currentOrder.getProdInfo().getLaborCostPerSqFoot()
                    + " " + currentOrder.getProdInfo().getMatCostPerSqFoot() + " " + currentOrder.getTotalMatCost()
                    + " " + currentOrder.getTotalLaborCost() + " " + currentOrder.getTotalTax() + " " + currentOrder.getSubTotal());

        }
        io.print("* * * * * * * * * * * *  * * *  * * * * * * * * * * * * * * * * * * *");

    }

    //Ask user Order Date and ItemNumber
    public Order getOrderInfo() {
        LocalDate date = io.readDate("Enter OrderDate(MM-dd-yyyy)");
        int orderNum = io.readInt("Enter OrderNumber");
        Order order = new Order();
        order.setDate(date);
        order.setOrderNum(orderNum);
        return order;
    }

    //Ask user Order Date  to display specific Order Date
    public Order getOrderForDate() {
        LocalDate date = io.readDate("Enter OrderDate(MM-dd-yyyy)");
        Order order = new Order();
        order.setDate(date);
        return order;
    }

    public void displayOrderForRemoveInfo(List<Order> OrderForRemove) {

        io.print("----------------------------------");
        for (Order currentOrder : OrderForRemove) {
            io.print(currentOrder.getOrderNum() + " " + currentOrder.getDate() + " " + currentOrder.getCustName() + " " + currentOrder.getProdInfo().getproductName()
                    + " " + currentOrder.getArea());
        }
        io.print("----------------------------------");
    }

    public void displayOrderForAdd(Order orderForAdd) {
        io.print("* * * * * * * * * * * * * * * *");
        io.print("Summary of the order");
        io.print("--------------------");
        io.print(orderForAdd.getDate() + " " + orderForAdd.getCustName() + " " + orderForAdd.getProdInfo().getproductName()
                + " " + orderForAdd.getArea());
        io.print("* * * * * * * * * * * * * * * *");
    }

    //DISPLAY PRODUCTS AVAILIBLE IN THE INVENTORY
    public void displayProductInfo(List<Product> productInfo) {
        int num = 1;
        io.print("Product Type\t Labor-Cost\t Material-Cost");
        for (Product currentProduct : productInfo) {
            io.print(num + " " + currentProduct.getproductName() + "\t" + currentProduct.getLaborCostPerSqFoot() + "\t" + currentProduct.getMatCostPerSqFoot());
            num++;
        }
    }

    //EDIT 
    public Order editOrder(Order currentOrder) {
        //List<Order> currentOrders=orderForEdit;


        Order updatedOrder = new Order();
        Product prodInfo = new Product();
        Tax taxInfo = new Tax();
        updatedOrder.setDate(currentOrder.getDate());
        updatedOrder.setOrderNum(currentOrder.getOrderNum());
        updatedOrder.setCustName(io.readString("Enter Customer Name: (" + currentOrder.getCustName() + ")"));
        if (updatedOrder.getCustName().trim().length() == 0) {
            updatedOrder.setCustName(currentOrder.getCustName());
        }
        prodInfo.setproductName(io.readString("Enter Product Type:(" + currentOrder.getProdInfo().getproductName() + ")"));
        updatedOrder.setProdInfo(prodInfo);
        if (updatedOrder.getProdInfo().getproductName().trim().length() == 0) {
            updatedOrder.setProdInfo(currentOrder.getProdInfo());
        }
        taxInfo.setstateAbbrev(io.readString("Enter State(ex.MN):(" + currentOrder.getTaxInfo().getstateAbbrev() + ")"));
        updatedOrder.setTaxInfo(taxInfo);
        if (updatedOrder.getTaxInfo().getstateAbbrev().trim().length() == 0) {
            updatedOrder.setTaxInfo(currentOrder.getTaxInfo());
        }
        updatedOrder.setArea(io.readMoney("Enter Area:(" + currentOrder.getArea() + ")"));
        if (updatedOrder.getArea().equals("")) {
            updatedOrder.setArea(currentOrder.getArea());
        }

        return updatedOrder;

    }

    public String orderConfirmation() {
        String option = io.readString("Are you sure to place order (Y/N)");
        return option;
    }

    public String removeConfirmation() {
        String option = io.readString("Are you sure to remove order (Y/N)");
        return option;
    }

    public int getMenu() {
        io.print("<<Flooring Program>>");
        io.print("1.Display Orders");
        io.print("2.Add an Order");
        io.print("3.Edit an Order");
        io.print("4.Remove an Order");
        io.print("5.Quit");
        return io.readInt("Enter your Option", 1, 5);
    }

    public void exitMode() {
        io.print("Thanks for visiting our system");
    }
     public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
