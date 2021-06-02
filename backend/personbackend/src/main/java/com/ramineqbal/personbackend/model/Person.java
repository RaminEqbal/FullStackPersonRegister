package com.ramineqbal.personbackend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity(name = "Person")
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private final Long id;

    private String surname;
    private String name;

    private Date dateOfBirth;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
    name = "person_address_join", 
    joinColumns = @JoinColumn(name = "person_id"), 
    inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addressList;




    
   

    public Person(@JsonProperty("id") Long id,@JsonProperty("surname") String surname,@JsonProperty("name") String name,@JsonProperty("dob") Date dateOfBirth,@JsonProperty("address") List<Address> address) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.addressList = address;
    }


    public Person(){
        this.id=-1l;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getAddress() {
        return addressList;
    }

    public void setAddress(List<Address> address) {
        this.addressList = address;
    }


    @Override
    public String toString() {
        return "Person [addressList=" + addressList + ", dateOfBirth=" + dateOfBirth + ", id=" + id + ", name=" + name
                + ", surname=" + surname + "]";
    }


}
