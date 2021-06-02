package com.ramineqbal.personbackend.repository;

import java.util.Optional;

import com.ramineqbal.personbackend.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
    //Spring automatically detects intent by analyzing the method signature (Method Query)
    void deleteAddressById(Long id);

    Optional<Address> findAddressById(Long id);



}
