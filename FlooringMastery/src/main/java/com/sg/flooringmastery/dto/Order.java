/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author dsmelser
 */
public class Order {

    private LocalDate date;
    private int orderNum;
    private String custName;
    private Product prodInfo;
    private Tax taxInfo;
    private BigDecimal area;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Tax getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(Tax taxInfo) {
        this.taxInfo = taxInfo;
    }

    public Product getProdInfo() {
        return prodInfo;
    }

    public void setProdInfo(Product prodInfo) {
        this.prodInfo = prodInfo;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getTotalMatCost() {

        //unit material cost * area
        BigDecimal toReturn
                = prodInfo.getMatCostPerSqFoot()
                        .multiply(area)
                        .setScale(2, RoundingMode.HALF_UP);

        return toReturn;

    }

    public BigDecimal getTotalLaborCost() {
        //unit labor cost * area
        BigDecimal toReturn
                = prodInfo.getLaborCostPerSqFoot()
                        .multiply(area)
                        .setScale(2, RoundingMode.HALF_UP);

        return toReturn;

    }

    public BigDecimal getTotalTax() {

        BigDecimal totalMatLaborCost
                = getTotalMatCost()
                        .add(getTotalLaborCost());

        BigDecimal totalTax
                = totalMatLaborCost
                        .multiply(taxInfo.getTaxRate())
                        .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

        return totalTax;

    }

    public BigDecimal getSubTotal() {

        BigDecimal subTotal
                = getTotalMatCost()
                        .add(getTotalLaborCost())
                        .add(getTotalTax());

        return subTotal;
    }

}
