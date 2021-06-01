package com.ramineqbal.personbackend.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ramineqbal.personbackend.model.Address;
import com.ramineqbal.personbackend.model.Person;

public class MockDatabase implements DatabaseInterface<Person> {
    
    List<Person> mockedList = new ArrayList<Person>();


    public MockDatabase(){

        List<Address> firstAddress =  new ArrayList<Address>(1);
        firstAddress.add(new Address("Musterstraße", 42, 47775, "Deutschland"));
        List<Address> secondAddress =  new ArrayList<Address>(1);
        secondAddress.add(new Address(
            "Musterstraße", 45, 47775, "Deutschland"));
        secondAddress.add(new Address(
            "Firmenallee", 37, 47775, "Deutschland"));
        List<Address> thirdAddress =  new ArrayList<Address>(1);
        thirdAddress.add(new Address(
            "Mümelmannstraße", 87, 47775, "Deutschland"));


        mockedList.add(new Person(UUID.randomUUID(),"Max", "Mustermann", new Date(), firstAddress));
        mockedList.add(new Person(UUID.randomUUID(),"Marie", "Musterfrau", new Date(), secondAddress));
        mockedList.add(new Person(UUID.randomUUID(),"Andre", "Tester", new Date(), thirdAddress));
    }




    @Override
    public List getAllValues() {
        return this.mockedList;
    }




    @Override
    public boolean addObject(Person object) {
        return this.mockedList.add(object);
    }




    @Override
    public Optional<Person> selectById(UUID id) {
        return mockedList.stream()
        .filter(person -> person.getId().equals(id))
        .findFirst();
    }




    @Override
    public boolean deleteById(UUID id) {
        return false;
    }




    @Override
    public boolean updateById(UUID id) {
        // TODO Auto-generated method stub
        return false;
    }




}
