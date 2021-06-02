package com.ramineqbal.personbackend.repository;

import java.util.Optional;

import com.ramineqbal.personbackend.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    //Spring automatically detects intent by analyzing the method signature (Method Query)
    void deletePersonById(Long id);

    Optional<Person> findPersonById(Long id);

    
    
}
