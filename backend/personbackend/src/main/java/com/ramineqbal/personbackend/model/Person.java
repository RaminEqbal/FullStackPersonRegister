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



/**
 * The Model Class of Person. Defined as an Entity and Serializable for proper integration with Spring's JPA Support to communicate with Databases
 */
@Entity(name = "Person")
public class Person implements Serializable {
    


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
     * Private field of the type String describing the surname
     */
    private String surname;

    /**
     * Private field fo the type String describing the name
     */
    private String name;

    /**
     * Private field of the type String describing the email address
     */
    private String emailAddress;


    /**
     * Private field of the type Date describing the date of birth
     */
    private Date dateOfBirth;


    /**
     * Private field that contains a list of Addresses that this Person has
     * JPAAnnotations:
     * <ul>
     * <li>ManyToMany: Describes that within the domain of relational entities, this is a many to many relation;</li>
     * <li>JoinTable: Describes the name of the join table and by which keys they are joined (person_id, address_id)</li>
     * </ul>
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
    name = "person_address_join", 
    joinColumns = @JoinColumn(name = "person_id"), 
    inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addressList;






    /**
     * Constructor of the Person model class, used as a way to define the needed properties for the JSON-to-Object parser of the Spring framework
     * @param id JSONKey: "id"
     * @param surname JSONKey: "surname"
     * @param name JSONKey: "name"
     * @param email JSONKey: "email"
     * @param dateOfBirth JSONKey: "dob"
     * @param address JSONKey: "address"
     */
    public Person(@JsonProperty("id") Long id,@JsonProperty("surname") String surname,@JsonProperty("name") String name,@JsonProperty("email") String email,@JsonProperty("dob") Date dateOfBirth,@JsonProperty("address") List<Address> address) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.emailAddress = email;
        this.dateOfBirth = dateOfBirth;
        this.addressList = address;
    }

    /**
     * Default Constructor
     */
    public Person(){
        this.id=-1l;
    }


    /**
     * Getters and setters
     */

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




    

    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Override
    public String toString() {
        return "Person [addressList=" + addressList + ", dateOfBirth=" + dateOfBirth + ", id=" + id + ", name=" + name
                + ", surname=" + surname + "]";
    }


}
