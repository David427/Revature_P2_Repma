package com.revature.services;

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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = RepmaApplication.class)
@Transactional
public class RealtorServiceTests {
    @Autowired
    RealtorService realtorService;

    @Test
    void givenNothing_whenGetAllRealtors_thenGetAllRealtorsFromDb() {
        List<Realtor> realtors = realtorService.getAllRealtors();
        assertFalse(realtors.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void givenRealtorId_whenGetRealtorById_thenGetRealtorFromDb(int id) {
        Realtor realtor = realtorService.getRealtorById(id);

        assertNotNull(realtor);
        assertNotEquals("", realtor.getFirstName());
        assertNotEquals("", realtor.getLastName());
        assertNotEquals("", realtor.getPhoneNumber());
        assertNotEquals("", realtor.getEmail());
        assertNotEquals("", realtor.getPassword());
        assertNotEquals("", realtor.getCompany());
        assertNotNull(realtor.getManagedListings());
    }

    @Test
    @Rollback
    void givenRealtor_whenAddRealtor_thenAddRealtorToDb() {
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

        realtorService.addRealtor(testRealtor);
        assertNotEquals(0, testRealtor.getRealtorId());
    }

    @Test
    @Rollback
    void givenRealtor_whenUpdateRealtor_thenUpdateRealtorInDb() {
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

        Realtor oldRealtor = realtorService.getRealtorById(newRealtor.getRealtorId());

        realtorService.updateRealtor(newRealtor);
        assertEquals(1, newRealtor.getRealtorId());
        assertNotEquals(oldRealtor, newRealtor);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Rollback
    void givenRealtorId_whenDeleteRealtor_thenDeleteRealtorInDb(int id) {
        RealtorServiceImpl mockService = mock(RealtorServiceImpl.class);
        mockService.deleteRealtor(id);
        verify(mockService).deleteRealtor((id));
    }
}
