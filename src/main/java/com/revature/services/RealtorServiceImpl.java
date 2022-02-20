package com.revature.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Listing;
import com.revature.models.Realtor;
import com.revature.repositories.RealtorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RealtorServiceImpl implements RealtorService {
    @Autowired
    RealtorRepo realtorRepo;

    @Autowired
    ListingService listingService;

    @Override
    public void addRealtor(Realtor r) {
        realtorRepo.save(r);
    }

    @Override
    public Realtor getRealtorById(int id) {
        return realtorRepo.findById(id).orElse(new Realtor());
    }

    @Override
    public List<Realtor> getAllRealtors() {
        return (List<Realtor>) realtorRepo.findAll();
    }

    @Override
    public Realtor updateRealtor(Realtor change) {
        return realtorRepo.save(change);
    }

    @Override
    public boolean deleteRealtor(int id) {
        try {
            realtorRepo.deleteById(id);
            return true;
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Realtor getRealtorByEmail(String email) {
        return realtorRepo.findByEmail(email);
    }

    @Override
    public boolean realtorLogin(String email, String pass) {
        Realtor r;

        if (getRealtorByEmail(email) != null) {
            r = getRealtorByEmail(email);
            return r.getPassword().equals(pass);
        }
        return false;
    }

    @Override
    public Realtor realtorRegistration(String jsonString) throws JsonProcessingException {

        Realtor jsonObject = new Realtor();
        ObjectMapper objMapper = new ObjectMapper();

        jsonObject = objMapper.readValue(jsonString, Realtor.class);

        addRealtor(jsonObject);
        return jsonObject;
    }

    // @Override
    // public Listing realtorAddListing(String jsonString) {
    //     ObjectMapper mapper = new ObjectMapper();
    //
    //     try {
    //         Listing jsonObject = mapper.readValue(jsonString, Listing.class);
    //
    //         listingService.addListing(jsonObject);
    //         return jsonObject;
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }
    //
    // @Override
    // public Listing realtorUpdateListing(String jsonString) {
    //     return null;
    // }
}
