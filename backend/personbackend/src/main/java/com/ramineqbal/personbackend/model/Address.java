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

/**
 * The Model Class of Address. Defined as an Entity and Serializable for proper integration with Spring's JPA Support to communicate with Databases
 */
@Entity(name =  "Address")
public class Address implements Serializable {
    

    /**
     * The private id field defined as long.<br>
     * JPAAnnotations:
     * <ul>
     * <li>Id: to clarify that this field is the identifier of the object</li>
     * <li>GeneratedValue: Specifies the Id Generation Type as AUTO</li>
     * <li>Column: Defines the column traits so that id has to be not null and can never be updated.</li>
     * </ul>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private final Long id;


    /**
     * Private field with the Type String that contains the name of the street of the Address
     */
    private String streetName;
    /**
     * Private field with the Type int that contains the number of the address within the street
     */
    private int streetNo;
    
    /**
     * Private field with the Type int that contains the Postal/ZIP code of the address
     */
    private int postalCode;

    /**
     * Private field with the Type String that contains the Country name of the address
     */
    private String countryName;



    /**
     * Private field that contains a list of Persons that this address is used by <br>
     * JPAAnnotations:
     * <ul>
     * <li>ManyToMany: Describes that within the domain of relational entities, this is a many to many relations; This ManyToMany relationship is mapped by com.ramineqbal.personbackend.model.Person.addressList; </li>
     * </ul>
     * 
     */
    @ManyToMany(mappedBy = "addressList",
    cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Person> usedBy;



    /**
     * Constructor of the Address model class, used as a way to define the needed properties for the JSON-to-Object parser of the Spring framework
     * @param id JSONKey: "id"
     * @param streetName JSONKey: "streetName"
     * @param streetNo JSONKey: "streetNo"
     * @param postalCode JSONKey: "postalCode"
     * @param countryName JSONKey: "countryName"
     */
    public Address(@JsonProperty("id") Long id,@JsonProperty("streetName") String streetName,@JsonProperty("streetNo") int streetNo,@JsonProperty("postalCode") int postalCode, @JsonProperty("countryName") String countryName) {
        this.id=id;
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.postalCode = postalCode;
        this.countryName = countryName;
    }

    /**
     * Default constructor
     */
    public Address() {
        this.id=-1l;
    }


    public boolean isValid() {
        if(this.streetName == null || this.countryName == null || this.postalCode == 0){
            return false;
        }
        return true;
    }




    /**
     * Getters and setters
     */

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
