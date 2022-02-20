package com.revature.repositories;

import com.revature.models.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends CrudRepository<Appointment, Integer> {

    public List<Appointment> findAllByClientId(int id);
    public List<Appointment> findAllByListingId(int id);

}
