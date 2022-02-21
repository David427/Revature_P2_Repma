package com.revature.services;

import com.revature.models.Appointment;
import com.revature.models.Client;
import com.revature.models.Listing;

import java.util.List;
import java.util.Set;

public interface ClientService {

    public void addClient(Client c);
    public Client getClientById(int id);
    public List<Client> getAllClients();
    public void updateClient(Client change);
    public boolean deleteClient(int id);

    Client getClientByEmail(String email);

    List<Appointment> getApptsByClient(int id);
    Set<Listing> getSavedListings(int id);
    public Client clientLogin(String email, String pass);

    Client clientRegistration(Client client);
}
