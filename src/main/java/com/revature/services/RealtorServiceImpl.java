package com.revature.services;

import com.revature.models.Realtor;
import com.revature.repositories.RealtorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealtorServiceImpl implements RealtorService {
    @Autowired
    RealtorRepo realtorRepo;

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
}
