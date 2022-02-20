package com.revature.controllers;

import com.revature.models.Listing;
import com.revature.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping
    public List<Listing> getAllListings(){
        return listingService.getAllListings();
    }

    @GetMapping(value = "/{listing_id}")
    public ResponseEntity<Listing> getListingById(@PathVariable("listing_id") Integer id) {
        Listing listing = listingService.getListingById(id);

        if (listing.getListingId() != 0) return new ResponseEntity<Listing>(listing, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public void addListing(@RequestBody Listing listing){
        listingService.addListing(listing);
    }

    @PutMapping(value = "/{listing_id}", consumes = "application/json", produces = "application/json")
    public Listing updateListing(@PathVariable("listing_id") int id, @RequestBody Listing l) {
        l.setListingId(id);
        return listingService.updateListing(l);
    }

    // @Authorized
    @DeleteMapping(value = "{listing_id}")
    public ResponseEntity<Listing> deleteListing(@PathVariable("listing_id") Integer id){
        boolean success = listingService.deleteListing(id);
        return new ResponseEntity<>((success) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND );
    }

}
