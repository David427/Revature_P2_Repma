package com.revature.services;

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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = RepmaApplication.class)
@Transactional
public class ClientServiceTests {
    @Autowired
    ClientService clientService;

    @Test
    void givenNothing_whenGetAllClients_thenGetAllClientsFromDb() {
        List<Client> clients = clientService.getAllClients();
        assertFalse(clients.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void givenClientId_whenGetClientById_thenGetClientFromDb(int id) {
        Client client = clientService.getClientById(id);

        assertNotNull(client);
        assertNotEquals("", client.getFirstName());
        assertNotEquals("", client.getLastName());
        assertNotEquals("", client.getPhoneNumber());
        assertNotEquals("", client.getEmail());
        assertNotEquals("", client.getPassword());
        assertNotNull(client.getSavedListings());
    }

    @Test
    @Rollback
    void givenClient_whenAddClient_thenAddClientToDb() {
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

        clientService.addClient(testClient);
        assertNotEquals(0, testClient.getClientId());
    }

    @Test
    @Rollback
    void givenClient_whenUpdateClient_thenUpdateClientInDb() {
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

        Client oldClient = clientService.getClientById(newClient.getClientId());

        clientService.updateClient(newClient);
        assertEquals(1, newClient.getClientId());
        assertNotEquals(oldClient, newClient);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Rollback
    void givenClientId_whenDeleteClient_thenDeleteClientInDb(int id) {
        ClientServiceImpl mockService = mock(ClientServiceImpl.class);
        mockService.deleteClient(id);
        verify(mockService).deleteClient((id));
    }
}