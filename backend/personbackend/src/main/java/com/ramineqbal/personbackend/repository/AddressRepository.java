package com.ramineqbal.personbackend.repository;

import java.util.Optional;

import com.ramineqbal.personbackend.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * AddressRepository class extending JpaRepository class; Will be instantiated via dependency injection by Spring and implements several database operations (inherited from JpaRepository). These contain several CRUD Operations.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    //Spring automatically detects intent by analyzing the method signature (Method Query)

    /**
     * Deletes an Address from the database according the input id
     * @param id The id of the Address to be deleted
     */
    void deleteAddressById(Long id);


    /**
     * Selects/finds an Address from the database according to the input id
     * @param id The id of the Address to be found
     * @return Either returns the Address Object from the database if it exists, or throws an AddressNotFoundException
     */
    Optional<Address> findAddressById(Long id);



}
