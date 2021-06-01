package com.ramineqbal.personbackend.database;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * General Operations a Database interface has to implement to support CRUD Operations
 */
public interface DatabaseInterface<E> {
    

    /**
     * 
     * @return All Values of the according model
     */
    List<E> getAllValues();

    boolean addObject(E object);



    /**
     * Select a specific entry based on the id
     * @param id The UUID of the to be selected Object
     * @return The to be selected object or null
     */
    Optional<E> selectById(UUID id);


    /**
     * Delete a specific entry based on the id
     * @param id The UUID of the to be deleted Object
     * @return true if Object could be deleted, else false
     */
    boolean deleteById(UUID id);



    /**
     * Update a specific entry based on the id
     * @param id The UUID of the to be updated Object
     * @return true if Object could be updated, else false
     */
    boolean updateById(UUID id);


}
