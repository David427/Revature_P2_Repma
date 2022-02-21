package com.revature.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.models.Realtor;

import java.util.List;

public interface RealtorService {

    public void addRealtor(Realtor r);
    public Realtor getRealtorById(int id);
    public List<Realtor> getAllRealtors();
    public Realtor updateRealtor(Realtor change);
    public boolean deleteRealtor(int id);

    Realtor getRealtorByEmail(String email);
    Realtor realtorLogin(String email, String pass);
    Realtor realtorRegistration(String jsonString) throws JsonProcessingException;
    // Listing realtorAddListing(String jsonString);
    // Listing realtorUpdateListing(String jsonString);
}
