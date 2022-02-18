package com.revature.services;

import com.revature.models.Client;

import java.util.List;

public interface ClientService {

    public void addClient(Client c);
    public Client getClientById(int id);
    public List<Client> getAllClients();
    public void updateClient(Client change);
    public boolean deleteClient(int id);

}
