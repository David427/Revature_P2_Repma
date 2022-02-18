package com.revature.services;

import com.revature.models.Realtor;

import java.util.List;

public interface RealtorService {

    public void addRealtor(Realtor r);
    public Realtor getRealtorById(int id);
    public List<Realtor> getAllRealtors();
    public Realtor updateRealtor(Realtor change);
    public boolean deleteRealtor(int id);
}
