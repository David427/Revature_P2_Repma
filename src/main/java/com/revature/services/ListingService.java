package com.revature.services;

import com.revature.models.Listing;

import java.util.List;

public interface ListingService {

    public void addListing(Listing l);
    public Listing getListingById(int id);
    public List<Listing> getAllListings();
    public Listing updateListing(Listing change);
    public boolean deleteListing(int id);

}
