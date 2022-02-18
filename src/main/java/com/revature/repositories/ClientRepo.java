package com.revature.repositories;

import com.revature.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends CrudRepository<Client, Integer> {

    //findByEmail
}
