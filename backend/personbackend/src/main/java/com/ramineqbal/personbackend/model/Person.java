package com.ramineqbal.personbackend.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class Person {
    
    @Id
    private final UUID id;

    private String surname;
    private String name;

    private Date dateOfBirth;


    @ManyToMany
    @JoinTable(
    name = "person_address_join", 
    joinColumns = @JoinColumn(name = "person_id"), 
    inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addressList;





    public Person(@JsonProperty("id") UUID id,@JsonProperty("surname") String surname,@JsonProperty("name") String name,@JsonProperty("dob") Date dateOfBirth,@JsonProperty("address") List<Address> address) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.addressList = address;
    }

    public UUID getId() {
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


    


}
