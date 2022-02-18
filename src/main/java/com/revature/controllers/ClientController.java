package com.revature.controllers;

import com.revature.models.Client;
import com.revature.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/profile")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") String id) {
        Client c = clientService.getClientById(Integer.parseInt(id));

        if (c.getClientId() != 0) return new ResponseEntity<Client>(c, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void updateClient(@PathVariable("id") int id, @RequestBody Client c){
        c.setClientId(id);
        clientService.updateClient(c);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public void addClient(@RequestBody Client c){
        clientService.addClient(c);
    }

    // @Authorized
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Integer id){
        boolean success = clientService.deleteClient(id);
        return new ResponseEntity<>((success) ?HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND );
    }
}
