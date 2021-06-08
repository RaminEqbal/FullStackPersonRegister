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



/**
 * AddressController Class is annotated with RestController, thus responsible for mapping API Requests to function calls
 * 
 ** This API uses the path /api/address
 */
@RequestMapping("/api/address")
@RestController
public class AddressController {
    

    /**
     * The database scheme and operations is implemented by AddressService and is dependency injected with the annotation Autowired
     */
    @Autowired
    private final AddressService database;


    /**
     * Constructor for the AddressController. Will be automatically instantiated by Spring
     * @param database This is the dependency injected service that will manage Database Operations
     */
    public AddressController(AddressService database) {
        this.database = database;
    }



    /**
     * Defines the Response for GET /api/address
     * @return A HTTP Response containing all Addresses
     */
    @GetMapping
    public ResponseEntity<List<Address>> getAddressList() {
        return new ResponseEntity<>(database.findAll(),HttpStatus.OK);

    }

   
    /**
     * Defines the Response for POST /api/address/add
     * @param Address The Address object to be added; annotated with RequestBody, thus this method will read the body of the POST request and parse it to an Address Object
     * @return HTTP Response (CREATED) with the added Object being also returned
     */
    @PostMapping("/add")
    public ResponseEntity<Address> addAddress(@RequestBody Address Address){
        return new ResponseEntity<>(database.addAddress(Address),HttpStatus.CREATED);
    }


    /**
     * Defines the Response for PUT /api/address/update
     * @param Address The Address object to be updated; annotated with RequestBody, thus this method will read the body of the PUT request and parse it to an Address Object
     * @return HTTP Response (OK) with the Updated Address Object
     */
    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@RequestBody Address Address) {
        return new ResponseEntity<>(database.updateAddress(Address),HttpStatus.OK);
    }


    /**
     * Defines the Response for GET /api/address/find/:id
     * @param id The id of the Address Object that this method should return; The value is read from the url within the :id part
     * @return HTTP Response (OK) with the to be selected object
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Address> findAddressById(@PathVariable("id") Long id) {

        Address toBeFound = database.findAddressById(id);
        return new ResponseEntity<>(toBeFound,HttpStatus.OK);



        
    }

    /**
     * Defines the Response for DELETE /api/address/delete/:id; Is used to delete an object, selected by its id
     * @param id The id of the Address Object that this method should delete; The value is read from the url within the :id part
     * @return HTTP Response (OK) after deletion.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id) {
        database.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }



}
