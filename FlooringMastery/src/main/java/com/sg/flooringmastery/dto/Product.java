/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author dsmelser
 */
public class Product {
    private String productName;
    private BigDecimal matCostPerSqFoot;
    private BigDecimal laborCostPerSqFoot;

    public String getproductName() {
        return productName;
    }
 
    public void setproductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getMatCostPerSqFoot() {
        return matCostPerSqFoot;
    }

    public void setMatCostPerSqFoot(BigDecimal matCostPerSqFoot) {
        this.matCostPerSqFoot = matCostPerSqFoot;
    }

    public BigDecimal getLaborCostPerSqFoot() {
        return laborCostPerSqFoot;
    }

    public void setLaborCostPerSqFoot(BigDecimal laborCostPerSqFoot) {
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }
    
}
