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
}
