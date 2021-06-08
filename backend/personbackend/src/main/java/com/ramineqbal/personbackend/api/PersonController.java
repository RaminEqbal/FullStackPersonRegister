package com.ramineqbal.personbackend.api;


import java.util.List;

import com.ramineqbal.personbackend.model.Person;
import com.ramineqbal.personbackend.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * PersonController Class is annotated with RestController, thus responsible for mapping API Requests to function calls
 * 
 * This API uses the path /api/person
 * Furthermore the annotation CrossOrigin allows cross origin accress to this API
 */
@CrossOrigin
@RequestMapping("/api/person")
@RestController
public class PersonController {
    
    /**
     * The database scheme and operations is implemented by PersonService and is dependency injected with the annotation Autowired
     */
    @Autowired
    private final PersonService database;

    /**
     * Constructor for the PersonController. Will be automatically instantiated by Spring
     * @param database This is the dependency injected service that will manage Database Operations
     */
    public PersonController(PersonService database) {
        this.database = database;
    }


    /**
     * Defines the Response for GET /api/person
     * @return A HTTP Response containing all Persons
     */
    @GetMapping
    public ResponseEntity<List<Person>> getPersonList() {
        return new ResponseEntity<>(database.findAll(),HttpStatus.OK);

    }

     


     /**
      * Defines the Response for POST /api/person/add
      * @param person The Person object to be added; annotated with RequestBody, thus this method will read the body of the POST request and parse it to a Person Object
      * @return HTTP Response (CREATED) with the added Object being also returned
      */
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        return new ResponseEntity<>(database.addPerson(person),HttpStatus.CREATED);
    }


    /**
     * Defines the Response for PUT /api/person/update
     * @param person The Person object to be updated; annotated with RequestBody, thus this method will read the body of the PUT request and parse it to a Person Object
     * @return HTTP Response (OK) with the updated Object being also returned
     */
    @PutMapping("/update")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        return new ResponseEntity<>(database.updatePerson(person),HttpStatus.OK);
    }



    /**
     * Defines the Response for GET /api/person/find/:id
     * @param id The id of the Person Object that this method should return; The value is read from the url within the :id part
     * @return HTTP Response (OK) with the to be selected object
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable("id") Long id) {

        Person toBeFound = database.findPersonById(id);
        return new ResponseEntity<>(toBeFound,HttpStatus.OK);



        
    }


    /**
     * Defines the Response for DELETE /api/person/delete/:id
     * @param id The id of the Address Object that this method should delete; The value is read from the url within the :id part
     * @return HTTP Response (OK) after deletion.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
        database.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }


}
