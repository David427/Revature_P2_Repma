package com.revature.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Appointment;
import com.revature.models.Client;
import com.revature.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("profile/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") String id) {
        Client c = clientService.getClientById(Integer.parseInt(id));

        if (c.getClientId() != 0) return new ResponseEntity<Client>(c, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "profile/{id}", consumes = "application/json", produces = "application/json")
    public void updateClient(@PathVariable("id") int id, @RequestBody Client c){
        c.setClientId(id);
        clientService.updateClient(c);
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public Client addClient(@RequestBody Client c) {
        return clientService.addClient(c);
    }

    @PostMapping(value = "/Login", consumes = "application/json", produces = "application/json")
    public Client clientLogin(@RequestBody Client c) {
        System.out.println(c);
        String email = c.getEmail();
        String pass = c.getPassword();
        return clientService.clientLogin(email, pass);
    }

    // @Authorized
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Integer id){
        boolean success = clientService.deleteClient(id);
        return new ResponseEntity<>((success) ?HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}
