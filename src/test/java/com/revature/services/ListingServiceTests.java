package com.revature.services;

import com.revature.app.RepmaApplication;
import com.revature.models.Client;
import com.revature.models.Listing;
import com.revature.models.Realtor;
import com.revature.repositories.ListingRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RepmaApplication.class)
@Transactional
public class ListingServiceTests {
    @Autowired
    ListingService listingService;

    @Autowired
    ListingRepo listingRepo;

    @Test
    void givenNothing_whenGetAllListings_thenGetAllListingsFromDb() {
        List<Listing> listings = listingService.getAllListings();
        assertFalse(listings.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void givenListingId_whenGetListingById_thenGetListingFromDb(int id) {
        Listing listing = listingService.getListingById(id);

        assertNotNull(listing);
        assertNotEquals("", listing.getAddress());
        assertNotEquals(0, listing.getBathrooms());
        assertNotEquals(0, listing.getBedrooms());
        assertNotEquals(0, listing.getSquareFt());
        assertNotEquals("", listing.getYearBuilt());
        assertNotEquals(0, listing.getPrice());
        assertNotEquals(0, listing.getLongitude());
        assertNotEquals(0, listing.getLatitude());
        assertNotNull(listing.getRealtor());
        assertNotNull(listing.getSavedByClients());
    }

    @Test
    @Rollback
    void givenListing_whenAddListing_thenAddListingToDb() {
        Set<Client> savedByClients = new HashSet<>();
        Set<Listing> managedListings = new HashSet<>();
        Realtor newRealtor = new Realtor(
                1,
                "Junit",
                "Test",
                "777-777-7777",
                "junit@test.com",
                "Password!",
                null,
                managedListings
        );
        Listing testListing = new Listing(
                0,
                "100 Junit Street",
                2,
                2,
                2000,
                "3000",
                999999,
                10.0000,
                10.0000,
                1,
                newRealtor,
                savedByClients
        );

        listingService.addListing(testListing);
        assertNotEquals(0, testListing.getListingId());
    }

    @Test
    @Rollback
    void givenListing_whenUpdateListing_thenUpdateListingInDb() {
        Set<Client> savedByClients = new HashSet<>();
        Set<Listing> managedListings = new HashSet<>();
        Realtor newRealtor = new Realtor(
                1,
                "Junit",
                "Test",
                "777-777-7777",
                "junit@test.com",
                "Password!",
                null,
                managedListings
        );
        Listing newListing = new Listing(
                1,
                "100 Junit Street",
                2,
                2,
                2000,
                "3000",
                999999,
                10.0000,
                10.0000,
                1,
                newRealtor,
                savedByClients
        );

        Listing oldListing = listingService.getListingById(newListing.getListingId());

        listingService.updateListing(newListing);
        assertEquals(1, newListing.getListingId());
        assertNotEquals(oldListing, newListing);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Rollback
    void givenListingId_whenDeleteListing_thenDeleteListingInDb(int id) {
        listingService.deleteListing(id);
        Optional<Listing> output = listingRepo.findById(id);
        assertFalse(output.isPresent());
    }
}
