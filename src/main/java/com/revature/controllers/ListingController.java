package com.revature.controllers;

import com.revature.models.Client;
import com.revature.models.Listing;
import com.revature.services.ClientService;
import com.revature.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @GetMapping(value = "/search/address")
    public List<Listing> searchByAddress(@RequestParam String address) {
        System.out.println(address);
        System.out.println(listingService.findByAddress(address));
        return listingService.findByAddress(address);
    }

    @GetMapping(value = "/search/price")
    public List<Listing> searchBelowPrice(@RequestParam String price) {
        System.out.println(price);
        double realPrice = Double.parseDouble(price);
        System.out.println(listingService.getLessThanPrice(realPrice));
        return listingService.getLessThanPrice(realPrice);
    }

    @GetMapping(value = "/search/bathrooms")
    public List<Listing> searchByBathrooms(@RequestParam String bathrooms) {
        System.out.println(bathrooms);
        int brooms = Integer.parseInt(bathrooms);
        System.out.println(listingService.findByBathrooms(brooms));
        return listingService.findByBathrooms(brooms);
    }

    @GetMapping(value = "/search/bedrooms")
    public List<Listing> searchByBedrooms(@RequestParam String bedrooms) {
        System.out.println(bedrooms);
        int brooms = Integer.parseInt(bedrooms);
        System.out.println(listingService.findByBedrooms(brooms));
        return listingService.findByBedrooms(brooms);
    }

    @PutMapping(value = "/{id}")
    public Client saveListing(@PathVariable("id") String id, @RequestBody Client c) {
        int listingId = Integer.parseInt(id);
        Listing newSavedListing = listingService.getListingById(listingId);

        Set<Listing> clientsListings = c.getSavedListings();
        clientsListings.add(newSavedListing);

        return c;
    }

}
