/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author siyaa
 */
public class NotFoundException extends Exception{
     public NotFoundException(String message) {
        super(message);
    }
    
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
