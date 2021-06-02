package com.ramineqbal.personbackend.service;

import java.util.List;

import com.ramineqbal.personbackend.exception.UserNotFoundException;
import com.ramineqbal.personbackend.model.Address;
import com.ramineqbal.personbackend.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressService {
    

    private final AddressRepository addressRepository;


    @Autowired
    public AddressService(AddressRepository addressRepo){
        this.addressRepository = addressRepo;
    }


    public Address addAddress(Address address){
        return addressRepository.save(address);
    }


    public List<Address> findAll() {
        return addressRepository.findAll();
    }


    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }


    public Address findAddressById(Long id) {
        return addressRepository.findAddressById(id)
        .orElseThrow(() -> new UserNotFoundException("address by id " + id + " not found!"));
    }



    public void deleteAddress(Long id) {
        addressRepository.deleteAddressById(id);
    }



}
