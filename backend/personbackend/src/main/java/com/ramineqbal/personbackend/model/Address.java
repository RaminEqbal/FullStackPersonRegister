package com.ramineqbal.personbackend.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity(name =  "Address")
public class Address implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private final Long id;


    private String streetName;
    private int streetNo;
    
    private int postalCode;

    private String countryName;


    @ManyToMany(mappedBy = "addressList",
    cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Person> usedBy;



    public Address(@JsonProperty("id") Long id,@JsonProperty("streetName") String streetName,@JsonProperty("streetNo") int streetNo,@JsonProperty("postalCode") int postalCode, @JsonProperty("countryName") String countryName) {
        this.id=id;
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.postalCode = postalCode;
        this.countryName = countryName;
    }

    public Address() {
        this.id=-1l;
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



    
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Address [countryName=" + countryName + ", id=" + id + ", postalCode=" + postalCode + ", streetName="
                + streetName + ", streetNo=" + streetNo + ", usedBy=" + usedBy + "]";
    }

    


}
