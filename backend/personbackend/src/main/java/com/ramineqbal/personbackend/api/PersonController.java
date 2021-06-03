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

@CrossOrigin
@RequestMapping("/api/person")
@RestController
public class PersonController {
    

    @Autowired
    private final PersonService database;


    public PersonController(PersonService database) {
        this.database = database;
    }



    @GetMapping
    public ResponseEntity<List<Person>> getPersonList() {
        return new ResponseEntity<>(database.findAll(),HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        return new ResponseEntity<>(database.addPerson(person),HttpStatus.CREATED);
    }



    @PutMapping("/update")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        return new ResponseEntity<>(database.updatePerson(person),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable("id") Long id) {

        Person toBeFound = database.findPersonById(id);
        return new ResponseEntity<>(toBeFound,HttpStatus.OK);



        
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
        database.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }


}
