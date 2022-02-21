package com.revature.services;

import com.revature.models.Listing;

import java.util.List;

public interface ListingService {

    public void addListing(Listing l);
    public Listing getListingById(int id);
    public List<Listing> getAllListings();
    public Listing updateListing(Listing change);
    public boolean deleteListing(int id);

    List<Listing> findByBedrooms(int bedrooms);
    List<Listing> findByAddress(String address);
    List<Listing> getLessThanPrice(double price);
    List<Listing> getGreaterThanPrice(double price);
    List<Listing> getBetweenPrice( double lowPrice, double highPrice);
    List<Listing> getGreaterThanSquareFeet(int squareFt);

}
