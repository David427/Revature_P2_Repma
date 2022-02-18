package com.revature.repositories;

import com.revature.models.Listing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepo extends CrudRepository<Listing, Integer> {
}
