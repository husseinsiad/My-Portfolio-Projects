/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingwithspring.dao;

/**
 *
 * @author siyaa
 */
public interface AuditLogDao {
     public void writeAuditEntry(String entry)throws VendingPersistenceException;
}
