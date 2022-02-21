package com.revature.repositories;

import com.revature.models.Listing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepo extends CrudRepository<Listing, Integer> {
    List<Listing> findByBedrooms(int bedrooms);
    List<Listing> findByBathrooms(int squareFt);

    @Query(value = "SELECT l from Listing l WHERE l.address LIKE '%'||:address||'%'")
    List<Listing> findByAddress(String address);

    // must use JPQL
    @Query(value = "SELECT l FROM Listing l WHERE l.price <= :price")
    List<Listing> getLessThanPrice(double price);

    @Query(value = "SELECT l FROM Listing l WHERE l.price >= :price")
    List<Listing> getGreaterThanPrice(double price);

    // @Query(value = "SELECT l FROM Listing l WHERE l.price BETWEEN :lowPrice AND :highPrice")
    // List<Listing> getBetweenPrice( double lowPrice, double highPrice);

    // @Query(value = "SELECT l FROM Listing l WHERE l.squareFt >= :squareFt")
    // List<Listing> getGreaterThanSquareFeet(int squareFt);
}
