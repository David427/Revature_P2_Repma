package com.revature.repositories;

import com.revature.app.RepmaApplication;
import com.revature.models.Client;
import com.revature.models.Listing;
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
public class ClientRepoTests {
    @Autowired
    private ClientRepo clientRepo;

    @Test
    void givenNothing_whenFindAll_thenGetAllClientsFromDb() {
        List<Client> clients = (List<Client>) clientRepo.findAll();
        assertFalse(clients.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void givenClientId_whenFindById_thenGetClientFromDb(int id) {
        Optional<Client> dbClient = clientRepo.findById(id);

        if (dbClient.isPresent()) {
            Client client = dbClient.get();
            assertNotNull(client);
            assertNotEquals("", client.getFirstName());
            assertNotEquals("", client.getLastName());
            assertNotEquals("", client.getPhoneNumber());
            assertNotEquals("", client.getEmail());
            assertNotEquals("", client.getPassword());
            assertNotNull(client.getSavedListings());
        }
    }

    @Test
    @Rollback
    void givenClient_whenSave_thenAddClientToDb() {
        Set<Listing> savedListings = new HashSet<>();
        Client testClient = new Client(
                0,
                "Junit",
                "Test",
                "777-777-7777",
                "test@junit.com",
                "Password!",
                savedListings
        );

        testClient = clientRepo.save(testClient);
        assertNotEquals(0, testClient.getClientId());

        Client getTestClient = clientRepo.findById(testClient.getClientId()).orElse(null);
        assertEquals(getTestClient, testClient);
    }

    @Test
    @Rollback
    void givenClient_whenSave_thenUpdateClientInDb() {
        Set<Listing> savedListings = new HashSet<>();
        Client newClient = new Client(
                1,
                "Junit",
                "Test",
                "777-777-7777",
                "test@junit.com",
                "Password!",
                savedListings
        );

        Optional<Client> oldClient = clientRepo.findById(newClient.getClientId());

        newClient = clientRepo.save(newClient);
        assertEquals(1, newClient.getClientId());
        assertNotEquals(oldClient, newClient);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Rollback
    void givenClientId_whenDeleteById_thenDeleteClientInDb(int id) {
        ClientRepo mockRepo = mock(ClientRepo.class);
        mockRepo.deleteById(id);
        verify(mockRepo).deleteById((id));
    }
}
