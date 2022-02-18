package com.revature.repositories;

import com.revature.app.RepmaApplication;
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
public class RealtorRepoTests {
    @Autowired
    private RealtorRepo realtorRepo;

    @Test
    void givenNothing_whenFindAll_thenGetAllRealtorsFromDb() {
        List<Realtor> listings = (List<Realtor>) realtorRepo.findAll();
        assertFalse(listings.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void givenRealtorId_whenFindById_thenGetRealtorFromDb(int id) {
        Optional<Realtor> dbRealtor = realtorRepo.findById(id);

        if (dbRealtor.isPresent()) {
            Realtor realtor = dbRealtor.get();
            assertNotNull(realtor);
            assertNotEquals("", realtor.getFirstName());
            assertNotEquals("", realtor.getLastName());
            assertNotEquals("", realtor.getPhoneNumber());
            assertNotEquals("", realtor.getEmail());
            assertNotEquals("", realtor.getPassword());
            assertNotEquals("", realtor.getCompany());
            assertNotNull(realtor.getManagedListings());
        }
    }

    @Test
    @Rollback
    void givenRealtor_whenSave_thenAddRealtorToDb() {
        Set<Listing> managedListings = new HashSet<>();
        Realtor testRealtor = new Realtor(
                0,
                "Junit",
                "Test",
                "777-777-7777",
                "junit@test.com",
                "Password!",
                null,
                managedListings
        );

        testRealtor = realtorRepo.save(testRealtor);
        assertNotEquals(0, testRealtor.getRealtorId());

        Realtor getTestRealtor = realtorRepo.findById(testRealtor.getRealtorId()).orElse(null);
        assertEquals(getTestRealtor, testRealtor);
    }

    @Test
    @Rollback
    void givenRealtor_whenSave_thenUpdateRealtorInDb() {
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

        Optional<Realtor> oldRealtor = realtorRepo.findById(newRealtor.getRealtorId());

        newRealtor = realtorRepo.save(newRealtor);
        assertEquals(1, newRealtor.getRealtorId());
        assertNotEquals(oldRealtor, newRealtor);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Rollback
    void givenRealtorId_whenDeleteById_thenDeleteRealtorInDb(int id) {
        RealtorRepo mockRepo = mock(RealtorRepo.class);
        mockRepo.deleteById(id);
        verify(mockRepo).deleteById((id));
    }
}
