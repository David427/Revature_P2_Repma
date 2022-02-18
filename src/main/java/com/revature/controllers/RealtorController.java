package com.revature.controllers;

import com.revature.models.Realtor;
import com.revature.services.RealtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/realtors")
public class RealtorController {

    @Autowired
    private RealtorService realtorService;

    @GetMapping
    public List<Realtor> getAllRealtors(){
        return realtorService.getAllRealtors();
    }

    @GetMapping(value = "/{realtor_id}")
    public ResponseEntity<Realtor> getRealtorById(@PathVariable("realtor_id") Integer id){
        Realtor r = realtorService.getRealtorById(id);

        if (r.getRealtorId() != 0) return new ResponseEntity<Realtor>(r, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{realtor_id}", consumes = "application/json", produces = "application/json")
    public Realtor updateRealtor(@PathVariable("realtor_id") int id, @RequestBody Realtor r){
        r.setRealtorId(id);
        return realtorService.updateRealtor(r);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public void addRealtor(@RequestBody Realtor r){
        realtorService.addRealtor(r);
    }

    // @Authorized
    @DeleteMapping(value = "/{realtor_id}")
    public ResponseEntity<Realtor> deleteRealtor(@PathVariable("realtor_id") Integer id){
        boolean success = realtorService.deleteRealtor(id);
        return new ResponseEntity<>((success) ?HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND );
    }

}
