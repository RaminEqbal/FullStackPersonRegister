package com.ramineqbal.personbackend.api;

import java.util.List;

import com.ramineqbal.personbackend.model.Address;
import com.ramineqbal.personbackend.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/address")
@RestController
public class AddressController {
    

    @Autowired
    private final AddressService database;


    public AddressController(AddressService database) {
        this.database = database;
    }



    @GetMapping
    public ResponseEntity<List<Address>> getAddressList() {
        return new ResponseEntity<>(database.findAll(),HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Address> addAddress(@RequestBody Address Address){
        return new ResponseEntity<>(database.addAddress(Address),HttpStatus.CREATED);
    }



    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@RequestBody Address Address) {
        return new ResponseEntity<>(database.updateAddress(Address),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Address> findAddressById(@PathVariable("id") Long id) {

        Address toBeFound = database.findAddressById(id);
        return new ResponseEntity<>(toBeFound,HttpStatus.OK);



        
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id) {
        database.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }



}
