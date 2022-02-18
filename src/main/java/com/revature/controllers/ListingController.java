package com.revature.controllers;

import com.revature.models.Listing;
import com.revature.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ListingController {
    @Autowired
    ListingService listingService;

    @GetMapping("/listings/{id}")
    public ResponseEntity<Listing> getListing(@PathVariable("id") String id) {
        Listing l = listingService.getListingById(Integer.parseInt(id));

        if (l.getListingId() != 0) return new ResponseEntity<Listing>(l, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
