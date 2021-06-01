package com.ramineqbal.personbackend.api;


import java.util.List;

import com.ramineqbal.personbackend.database.DatabaseInterface;
import com.ramineqbal.personbackend.database.MockDatabase;
import com.ramineqbal.personbackend.model.Person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/person")
@RestController
public class PersonController {
    


    DatabaseInterface<Person> database = new MockDatabase();




    @GetMapping
    public List<Person> getPersonList() {
        
        return database.getAllValues();

    }


}
