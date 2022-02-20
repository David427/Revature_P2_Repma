package com.revature.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Appointment;
import com.revature.models.Client;
import com.revature.models.Listing;
import com.revature.repositories.AppointmentRepo;
import com.revature.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    AppointmentRepo appointmentRepo;

    @Override
    public void addClient(Client c) {
        clientRepo.save(c);
    }

    @Override
    public Client getClientById(int id) {
        return clientRepo.findById(id).orElse(new Client());
    }

    @Override
    public List<Client> getAllClients() {
        return (List<Client>) clientRepo.findAll();
    }

    @Override
    public void updateClient(Client change) {
        clientRepo.save(change);
    }

    @Override
    public boolean deleteClient(int id) {
        try {
            clientRepo.deleteById(id);
            return true;
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepo.findByEmail(email);
    }

    @Override
    public List<Appointment> getApptsByClient(int id) {
        return appointmentRepo.findAllByClientId(id);
    }

    @Override
    public Set<Listing> getSavedListings(int id) {
        Client client = clientRepo.findById(id).orElse(new Client());
        return client.getSavedListings();
    }

    @Override
    public boolean clientLogin(String email, String pass) {
        Client c;

        if (getClientByEmail(email) != null) {
            c = getClientByEmail(email);
            return c.getPassword().equals(pass);
        }
        return false;
    }

    @Override
    public Client clientRegistration(String jsonString) throws JsonProcessingException {

        Client jsonObject = new Client();
        ObjectMapper objectMapper = new ObjectMapper();

        jsonObject = objectMapper.readValue(jsonString, Client.class);

        addClient(jsonObject);
        return jsonObject;
    }
}
