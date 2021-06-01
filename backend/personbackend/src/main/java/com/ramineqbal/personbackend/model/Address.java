package com.ramineqbal.personbackend.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Address {
    
    @Id
    public UUID id;


    public String streetName;
    public int streetNo;
    
    public int postalCode;

    public String countryName;


    @ManyToMany
    public List<Person> usedBy;



    public Address(String streetName, int streetNo, int postalCode, String countryName) {
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.postalCode = postalCode;
        this.countryName = countryName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    


}
