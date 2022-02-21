package com.revature.services;

import com.revature.models.Listing;
import com.revature.repositories.ListingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {
    @Autowired
    ListingRepo listingRepo;

    @Override
    public void addListing(Listing l) {
        listingRepo.save(l);
    }

    @Override
    public Listing getListingById(int id) {
        return listingRepo.findById(id).orElse(new Listing());
    }

    @Override
    public List<Listing> getAllListings() {
        return (List<Listing>) listingRepo.findAll();
    }

    @Override
    public Listing updateListing(Listing change) {
        return listingRepo.save(change);
    }

    @Override
    public boolean deleteListing(int id) {
        try {
            listingRepo.deleteById(id);
            return true;
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Listing> findByBathrooms(int bathrooms){
        return listingRepo.findByBedrooms(bathrooms);
    }

    @Override
    public List<Listing> findByBedrooms(int bedrooms){
        return listingRepo.findByBedrooms(bedrooms);
    }

    @Override
    public List<Listing> findByAddress(String address){
        return listingRepo.findByAddress(address);
    }

    @Override
    public List<Listing> getLessThanPrice(double price){
        return listingRepo.getLessThanPrice(price);
    }

    @Override
    public List<Listing> getGreaterThanPrice(double price){
        return listingRepo.getGreaterThanPrice(price);
    }

    // @Override
    // public List<Listing> getBetweenPrice( double lowPrice, double highPrice){
    //     return listingRepo.getBetweenPrice(lowPrice, highPrice);
    // }
    //
    // @Override
    // public List<Listing> getGreaterThanSquareFeet(int squareFt){
    //     return listingRepo.getGreaterThanSquareFeet(squareFt);
    // }
}
