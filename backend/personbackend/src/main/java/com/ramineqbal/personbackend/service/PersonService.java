package com.ramineqbal.personbackend.service;

import java.util.List;

import com.ramineqbal.personbackend.exception.AddressNotFoundException;
import com.ramineqbal.personbackend.exception.PersonNotFoundException;
import com.ramineqbal.personbackend.model.Address;
import com.ramineqbal.personbackend.model.Person;
import com.ramineqbal.personbackend.repository.AddressRepository;
import com.ramineqbal.personbackend.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Example;



/**
 * PersonService class, annotated with Service (origin: org.springframework.stereotype.Service)
 * Used as intermediary class between PersonRepository (Database-Interface) and PersonController (API-Controller) and essentially implements the functionality of the methods invoked by the API.
 */
@Service
public class PersonService {

    /**
     * This is the repository interface to the PersonRepository used as a Data Access Layer to the Database; Will be dependency injected by Spring into this object
     */
    private final PersonRepository personRepository;


    /**
     * This is the repository interface to the AddressRepository used as a Data Access Layer to the Database; Will be dependency injected by Spring into this object;
     * Is used to map already existing databases to newly added persons before creating a new address
     */
    private final AddressRepository addressRepository;

    /**
     * Constructor in which the dependecy injections are instantiated
     */
    @Autowired
    public PersonService(PersonRepository personRepo, AddressRepository addressRepository){
        this.personRepository = personRepo;
        this.addressRepository = addressRepository;
    }

    /**
     * Adds a person to the database. If the Address Information within the Person Object contains an address that matches an address in the database, then the according database object will be mapped to that specific person
     * @param person The Person Object that is to be added to the database
     * @return The Person Object that has been added to the Database
     */
    @Transactional
    public Person addPerson(Person person){
        Person mutatedPerson = person;
        List<Address> addressList = mutatedPerson.getAddress();


        //Check if similar Address already exists by Data Example
        //Iterate through the Address List of the Person Object
        for(int i =0;i<addressList.size();i++){
            
            //Creates Example-Address Instance of an Address within the Person Object 
            Example<Address> addressLookup = Example.of(addressList.get(i));

            //Checks if that specific example exists within the database, if yes, replace that address within the person object with an Address Instance of the found Database Object
            if(addressRepository.exists(addressLookup)) {
                Address replace = addressRepository.findOne(addressLookup).orElseThrow(() -> new AddressNotFoundException("There was an error finding an Address that should allegedly have been in the Database"));
                addressList.set(i, replace);
            }
        }

        mutatedPerson.setAddress(addressList);

        return personRepository.save(mutatedPerson);
    }

    /**
     * Queries for all Person Entries in the Database
     * @return List of all Persons that have a record in the database
     */
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }


    /**
     * Updates a Person in the Database based on all containing attributes/keys of the Person object
     * @param person A Person Object that will be compared to an existing Person object in the database and overwritten
     * @return The instance of the updated Person Object.
     */
    @Transactional
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Queries for a person, conditioning the result by the id
     * @param id The id of the person that is queried for
     * @return The instance of the person that has the specified id
     */
    @Transactional(readOnly = true)
    public Person findPersonById(Long id) {
        return personRepository.findPersonById(id)
        .orElseThrow(() -> new PersonNotFoundException("Person by id " + id + " not found!"));
    }

    /**
     * Deletes a Person from the Database based on the ID:
     * Query: DELETE FROM PERSON WHERE id={id}
     * @param id The id of the person that is to be deleted
     */
    @Transactional
    public void deletePerson(Long id) {
        personRepository.deletePersonById(id);
    }




}
