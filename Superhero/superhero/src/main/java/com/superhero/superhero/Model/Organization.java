/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superhero.superhero.Model;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author siyaa
 */
public class Organization {

    private int id;
    @NotBlank(message = " Organization name must not be empty.")
    @Size(max = 30, message = "Oraganization name must be less than 30 characters.")
    private String name;
    private String desc;
    @NotBlank(message = " Address  must not be empty.")
    @Size(max = 30, message = "Address  must be less than 30 characters.")
    private String address;
    @NotBlank(message = " Contact must not be empty.")
    @Size(max = 30, message = "Contact must be less than 30 characters.")
    private String contact;

    private List<Superhero> superheros;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Superhero> getSuperheros() {
        return superheros;
    }

    public void setSuperheros(List<Superhero> superheros) {
        this.superheros = superheros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.desc);
        hash = 71 * hash + Objects.hashCode(this.address);
        hash = 71 * hash + Objects.hashCode(this.contact);
        hash = 71 * hash + Objects.hashCode(this.superheros);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.desc, other.desc)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.superheros, other.superheros)) {
            return false;
        }
        return true;
    }

}
