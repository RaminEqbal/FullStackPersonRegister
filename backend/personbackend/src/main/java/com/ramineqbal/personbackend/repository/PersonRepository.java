package com.ramineqbal.personbackend.repository;

import java.util.Optional;

import com.ramineqbal.personbackend.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;



/**
 * PersonRepository class extending JpaRepository class; Will be instantiated via dependency injection by Spring and implements several database operations (inherited from JpaRepository). These contain several CRUD Operations.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    //Spring automatically detects intent by analyzing the method signature (Method Query)

    /**
     * Deletes a Person from the database according the input id
     * @param id The id of the Person to be deleted
     */
    void deletePersonById(Long id);


    /**
     * Selects/finds a Person from the database according to the input id
     * @param id The id of the Person to be found
     * @return Either returns the Person Object from the database if it exists, or throws a PersonNotFoundException
     */
    Optional<Person> findPersonById(Long id);

    
    
}
