/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.NotFoundException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dsmelser
 */
public class orderDaoImpl implements orderDao {

    String folder;
//    private Map<String, Product> products = new HashMap<>();
//    private Map<String, Tax> taxes = new HashMap<>();
//    private Map<String, Order> orders = new HashMap<>();
    static final String delimiter = ",";

    public orderDaoImpl(String folder) {
        this.folder = folder;
    }

    @Override
    public List<Order> getOrdersForDate(LocalDate date) throws PersistenceException {
        List<Order> ordersForDate = new ArrayList<>();

        try {
            String filePath = convertDateToPath(date);

            Scanner scn = new Scanner(new BufferedReader(new FileReader(filePath)));

            String line = scn.nextLine();

            while (scn.hasNextLine()) {
                line = scn.nextLine();

                String[] cells = line.split(",");
                Order toBuild = new Order();
                toBuild.setDate(date);
                toBuild.setOrderNum(Integer.parseInt(cells[0]));
                toBuild.setCustName(cells[1]);

                Tax taxInfo = new Tax();
                taxInfo.setstateAbbrev(cells[2]);
                taxInfo.setTaxRate(new BigDecimal(cells[3]));
                toBuild.setTaxInfo(taxInfo);

                Product prodInfo = new Product();
                prodInfo.setproductName(cells[4]);
                prodInfo.setMatCostPerSqFoot(new BigDecimal(cells[6]));
                prodInfo.setLaborCostPerSqFoot(new BigDecimal(cells[7]));
                toBuild.setProdInfo(prodInfo);

                toBuild.setArea(new BigDecimal(cells[5]));

                ordersForDate.add(toBuild);
            }
            
            scn.close();

        } catch (FileNotFoundException ex) {

        }
        return ordersForDate;
    }

    @Override
    public Order addOrder(Order toAdd) throws PersistenceException {
        if (toAdd == null) {
            throw new PersistenceException("Tried to add null Order.");
        }
        List<Order> orders = getOrdersForDate(toAdd.getDate());
        int nextId = calculateNextId(orders);
        toAdd.setOrderNum(nextId);
        orders.add(toAdd);
        writeOrder(orders, toAdd.getDate());
        return toAdd;
    }

    @Override
    public Order editOrder(Order newData) throws PersistenceException {
        if (newData == null) {
            throw new PersistenceException("Tried to update null Order.");
        }
        List<Order> orders = getOrdersForDate(newData.getDate());
        int matchedIndex = -1;
        for (int i = 0; i < orders.size(); i++) {
            Order toCheck = orders.get(i);
            if (toCheck.getOrderNum() == (newData.getOrderNum())) {
                matchedIndex = i;
                break;
            }
        }
        orders.remove(matchedIndex);
        orders.add(newData);

        writeOrder(orders, newData.getDate());
        return newData;
    }

    @Override
    public void removeOrder(Order order) throws PersistenceException {
        //loadOrder();

        if (order == null) {
            throw new PersistenceException("Tried to Remove null order");
        }
        List<Order> ordersForDay = getOrdersForDate(order.getDate());
        //find order that matches and remove at that position in the list
        int matchingIndex = -1;
        for (int i = 0; i < ordersForDay.size(); i++) {
            Order toCheck = ordersForDay.get(i);
            if (toCheck.getOrderNum() == (order.getOrderNum())) {
                matchingIndex = i;
                break;
            }
        }
        ordersForDay.remove(matchingIndex);
        //call the write 
        writeOrder(ordersForDay, order.getDate());
    }

    @Override
    public Product getProductByName(Product product) throws NotFoundException, PersistenceException {
        List<Product> allProducts = loadProduct();
        Product newProduct = allProducts
                .stream()
                .filter(p -> p.getproductName().equalsIgnoreCase(product.getproductName()))
                .findFirst().orElse(null);
        if (newProduct != null) {
            return newProduct;
        } else {
            throw new PersistenceException("Product Type was not Found");
        }

    }

    @Override
    public Tax getTax(Tax state) throws PersistenceException, NotFoundException {
        List<Tax> allTaxes = loadTaxInfo();
        Tax newTax = allTaxes
                .stream()
                .filter(t -> t.getstateAbbrev().equalsIgnoreCase(state.getstateAbbrev()))
                .findFirst().orElse(null);

        if (newTax != null) {
            return newTax;
        } else {
            throw new NotFoundException("State Abbreviation was not Found");
        }
    }

    @Override
    public List<Product> getAllProduct() throws PersistenceException {
        //loadProduct();
        return new ArrayList<>(loadProduct());
    }

    private String convertDateToPath(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String fileName = "Orders_" + date.format(formatter) + ".txt";

        Path generatedPath = Paths.get(folder, fileName);

        return generatedPath.toString();
    }

    private String productPath() {
        String fileName = "Products" + ".txt";
        Path generatedPath = Paths.get(folder, fileName);
        return generatedPath.toString();
    }

    private String TaxPath() {
        String fileName = "Taxes" + ".txt";
        Path generatedPath = Paths.get(folder, fileName);
        return generatedPath.toString();
    }

    private List<Product> loadProduct() throws PersistenceException {

        List<Product> listOfProducts = new ArrayList<>();
        try {
            String filePath = productPath();

            Scanner scn = new Scanner(new BufferedReader(new FileReader(filePath)));

            String line = scn.nextLine();

            while (scn.hasNextLine()) {
                line = scn.nextLine();

                String[] cells = line.split(",");
                Product product = new Product();
                product.setproductName(cells[0]);
                product.setLaborCostPerSqFoot(new BigDecimal(cells[1]));
                product.setMatCostPerSqFoot(new BigDecimal(cells[2]));
                //products.put(product.getproductName(), product);
                listOfProducts.add(product);
            }
            scn.close();
        } catch (FileNotFoundException ex) {
//            throw new PersistenceException(
//                    "-_- Could not load Product data into memory.", ex);
        }

        return listOfProducts;

    }

    private List<Tax> loadTaxInfo() throws PersistenceException {
        List<Tax> listOfTax = new ArrayList<>();
        try {
            String filePath = TaxPath();

            Scanner scn = new Scanner(new BufferedReader(new FileReader(filePath)));

            String line = scn.nextLine();

            while (scn.hasNextLine()) {
                line = scn.nextLine();
                String[] cells = line.split(",");
                Tax tax = new Tax();
                tax.setstateAbbrev(cells[0]);
                tax.setTaxRate(new BigDecimal(cells[1]));
                listOfTax.add(tax);
            }
            scn.close();
        } catch (FileNotFoundException ex) {
            throw new PersistenceException(
                    "-_- Could not load Taxes Info into memory.", ex);
        }
        return listOfTax;

    }

    private void writeOrder(List<Order> orders, LocalDate date) throws PersistenceException {
        PrintWriter fileWriter = null;
        //LocalDate date = LocalDate.now();
        try {
            String filePath = convertDateToPath(date);
            fileWriter = new PrintWriter(new FileWriter(filePath));
            fileWriter.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");

            for (Order toWrite : orders) {

                String line = toWrite.getOrderNum() + delimiter
                        + toWrite.getCustName() + delimiter
                        + toWrite.getTaxInfo().getstateAbbrev() + delimiter
                        + toWrite.getTaxInfo().getTaxRate() + delimiter
                        + toWrite.getProdInfo().getproductName() + delimiter
                        + toWrite.getArea() + delimiter
                        + toWrite.getProdInfo().getLaborCostPerSqFoot() + delimiter
                        + toWrite.getProdInfo().getMatCostPerSqFoot() + delimiter
                        + toWrite.getTotalLaborCost() + delimiter
                        + toWrite.getTotalMatCost() + delimiter
                        + toWrite.getTotalTax() + delimiter
                        + toWrite.getSubTotal();

                fileWriter.println(line);

            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            throw new PersistenceException("Order,Could not write to File ", ex);
        } finally {
            fileWriter.close();
        }

    }

    private int calculateNextId(List<Order> order) {
        int nextId = order.stream().mapToInt(o -> o.getOrderNum()).max().orElse(0) + 1;
        return nextId;

    }

//    @Override
//    public List<Order> getOrder(Order orderInfo) throws PersistenceException {
//        List<Order> ordersForDate = new ArrayList<>();
//
//        try {
//            String filePath = convertDateToPath(orderInfo.getDate());
//
//            Scanner scn = new Scanner(new BufferedReader(new FileReader(filePath)));
//
//            String line = scn.nextLine();
//
//            while (scn.hasNextLine()) {
//                line = scn.nextLine();
//
//                String[] cells = line.split(",");
//                Order toBuild = new Order();
//                toBuild.setDate(orderInfo.getDate());
//                toBuild.setOrderNum(Integer.parseInt(cells[0]));
//                toBuild.setCustName(cells[1]);
//
//                Tax taxInfo = new Tax();
//                taxInfo.setstateAbbrev(cells[2]);
//                taxInfo.setTaxRate(new BigDecimal(cells[3]));
//                toBuild.setTaxInfo(taxInfo);
//
//                Product prodInfo = new Product();
//                prodInfo.setproductName(cells[4]);
//                prodInfo.setLaborCostPerSqFoot(new BigDecimal(cells[6]));
//                prodInfo.setMatCostPerSqFoot(new BigDecimal(cells[7]));
//                toBuild.setProdInfo(prodInfo);
//
//                toBuild.setArea(new BigDecimal(cells[5]));
//
//                ordersForDate.add(toBuild);
//
//            }
//
//        } catch (FileNotFoundException ex) {
//            throw new PersistenceException("Could not open file", ex);
//        }
//        return ordersForDate;
//    }

    

}
