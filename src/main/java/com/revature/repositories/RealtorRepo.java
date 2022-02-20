package com.revature.repositories;

import com.revature.models.Client;
import com.revature.models.Realtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtorRepo extends CrudRepository<Realtor, Integer> {
    Realtor findByEmail(String email);
}
