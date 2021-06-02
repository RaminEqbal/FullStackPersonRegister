package com.ramineqbal.personbackend.service;

import java.util.List;

import com.ramineqbal.personbackend.exception.UserNotFoundException;
import com.ramineqbal.personbackend.model.Address;
import com.ramineqbal.personbackend.model.Person;
import com.ramineqbal.personbackend.repository.AddressRepository;
import com.ramineqbal.personbackend.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Example;



@Service
public class PersonService {

    
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;


    @Autowired
    public PersonService(PersonRepository personRepo, AddressRepository addressRepository){
        this.personRepository = personRepo;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Person addPerson(Person person){
        Person mutatedPerson = person;
        List<Address> addressList = mutatedPerson.getAddress();


        //Check if similar Address already exists by Data Example
        for(int i =0;i<addressList.size();i++){
            System.out.println(addressList.get(i).toString());
            Example<Address> addressLookup = Example.of(addressList.get(i));
            if(addressRepository.exists(addressLookup)) {
                System.out.println("Lookup Matched");
                Address replace = addressRepository.findOne(addressLookup).orElseThrow(() -> new RuntimeException("Test"));
                addressList.set(i, replace);
            }
        }

        mutatedPerson.setAddress(addressList);

        return personRepository.save(mutatedPerson);
    }

    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public Person findPersonById(Long id) {
        return personRepository.findPersonById(id)
        .orElseThrow(() -> new UserNotFoundException("Person by id " + id + " not found!"));
    }


    @Transactional
    public void deletePerson(Long id) {
        personRepository.deletePersonById(id);
    }




}
