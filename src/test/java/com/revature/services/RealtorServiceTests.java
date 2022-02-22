package com.revature.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.app.RepmaApplication;
import com.revature.models.Client;
import com.revature.models.Listing;
import com.revature.models.Realtor;
import com.revature.repositories.RealtorRepo;
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
public class RealtorServiceTests {
    @Autowired
    RealtorService realtorService;

    @Autowired
    RealtorRepo realtorRepo;

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
        realtorService.deleteRealtor(id);
        Optional<Realtor> output = realtorRepo.findById(id);
        assertFalse(output.isPresent());
    }

    @Test
    void givenEmail_whenFindByEmail_thenGetRealtor() {
        String email = "realtor1@gmail.com";
        Realtor output = realtorService.getRealtorByEmail(email);
        assertEquals(1, output.getRealtorId());
    }

    @Test
    void givenEmailandPass_whenLogin_thenClientLogin() {
        String email = "realtor1@gmail.com";
        String password = "Password!";

        Realtor realtor = realtorService.realtorLogin(email, password);

        assertNotNull(realtorService.realtorLogin(email, password));
        assertNotEquals(0, realtor.getRealtorId());
    }

    @Test
    void givenWrongPass_whenLogin_thenFailClientLogin() {
        String email = "email1@gmail.com";
        String password = "Password!!!";

        Realtor realtor = realtorService.realtorLogin(email, password);

        assertEquals(0, realtor.getRealtorId());
    }

    @Test
    void givenJSON_whenAddClient_thenAddClient() throws JsonProcessingException {
        String jsonString = "{" +
                "\"realtorId\":\"0\"," +
                "\"firstName\":\"Fake\"," +
                "\"lastName\":\"Person\"," +
                "\"email\":\"realtor4@gmail.com\"," +
                "\"phoneNumber\":\"999-888-7777\"," +
                "\"password\":\"Password!\"" +
                "}";

        Realtor output = realtorService.realtorRegistration(jsonString);
        assertEquals("Fake", output.getFirstName());
        assertNotEquals(0, output.getRealtorId());
    }
}
