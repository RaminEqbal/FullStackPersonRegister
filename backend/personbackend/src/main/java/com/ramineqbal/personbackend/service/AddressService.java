package com.ramineqbal.personbackend.service;

import java.util.List;

import com.ramineqbal.personbackend.exception.AddressNotFoundException;
import com.ramineqbal.personbackend.model.Address;
import com.ramineqbal.personbackend.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * AddressService class, annotated with Service (origin: org.springframework.stereotype.Service)
 * Used as intermediary class between AddressRepository (Database-Interface) and AddressController (API-Controller) and essentially implements the functionality of the methods invoked by the API
 */
@Service
public class AddressService {
    
    /**
     * This is the repository interface to the AddressRepository used as a Data Access Layer to the Database; Will be dependency injected by Spring into this object
     */
    private final AddressRepository addressRepository;


    /**
     * Constructor which will instantiate AddressService with a dependency injected instance of AddressRepository
     * @param addressRepo The AddressRepository that will be injected by Spring
     */
    @Autowired
    public AddressService(AddressRepository addressRepo){
        this.addressRepository = addressRepo;
    }


    /**
     * Adds an address to the database
     * @param address The Address to be added to the Database
     * @return The input address that has been saved
     */
    public Address addAddress(Address address){
        return addressRepository.save(address);
    }


    /**
     * Queries for all addresses that are contained in the database
     * @return A List of all Addresses parsed from the Database entries
     */
    public List<Address> findAll() {
        return addressRepository.findAll();
    }


    /**
     * Updates an adress in the Database based on all containing attributes/keys of the address object
     * @param address An Address Object that will be compared to an existing address object in the database and overwritten
     * @return The overwritten Address Object
     */
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    /**
     * Queries for an address, conditioning the result by the id
     * @param id The id of the address that is queried
     * @return The Address object that has been queried if it exists, else it returns an AddressNotFoundException
     */
    public Address findAddressById(Long id) {
        return addressRepository.findAddressById(id)
        .orElseThrow(() -> new AddressNotFoundException("address by id " + id + " not found!"));
    }


    /**
     * Deletes an Address from the Database based on the ID:
     * Query: DELETE FROM ADDRESS WHERE id={id}
     * @param id The id of the address that is to be deleted
     */
    public void deleteAddress(Long id) {
        addressRepository.deleteAddressById(id);
    }



}
