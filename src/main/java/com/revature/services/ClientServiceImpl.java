package com.revature.services;

import com.revature.models.Client;
import com.revature.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepo clientRepo;

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
}
