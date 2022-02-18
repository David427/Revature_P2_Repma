package com.revature.repositories;

import com.revature.app.RepmaApplication;
import com.revature.models.Client;
import com.revature.models.Listing;
import com.revature.models.Realtor;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = RepmaApplication.class)
@Transactional
public class ListingRepoTests {
    @Autowired
    private ListingRepo listingRepo;

    @Test
    void givenNothing_whenFindAll_thenGetAllListingsFromDb() {
        List<Listing> listings = (List<Listing>) listingRepo.findAll();
        assertFalse(listings.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void givenListingId_whenFindById_thenGetListingFromDb(int id) {
        Optional<Listing> dbListing = listingRepo.findById(id);

        if (dbListing.isPresent()) {
            Listing listing = dbListing.get();
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
    }

    @Test
    @Rollback
    void givenListing_whenSave_thenAddListingToDb() {
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

        testListing = listingRepo.save(testListing);
        assertNotEquals(0, testListing.getListingId());

        Listing getTestListing = listingRepo.findById(testListing.getListingId()).orElse(null);
        assertEquals(getTestListing, testListing);
    }

    @Test
    @Rollback
    void givenListing_whenSave_thenUpdateListingInDb() {
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

        Optional<Listing> oldListing = listingRepo.findById(newListing.getListingId());

        newListing = listingRepo.save(newListing);
        assertEquals(1, newListing.getListingId());
        assertNotEquals(oldListing, newListing);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Rollback
    void givenListingId_whenDeleteById_thenDeleteListingInDb(int id) {
        ListingRepo mockRepo = mock(ListingRepo.class);
        mockRepo.deleteById(id);
        verify(mockRepo).deleteById((id));
    }
}
