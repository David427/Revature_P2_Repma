package com.revature.services;

import com.revature.models.Appointment;
import com.revature.repositories.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentRepo appointmentRepo;

    @Override
    public Appointment addAppointment(Appointment appt) {
        return appointmentRepo.save(appt);
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentRepo.findById(id).orElse(new Appointment());
    }

    @Override
    public List<Appointment> getAllAppointmentsByClient(int id) {
        return appointmentRepo.findAllByClientId(id);
    }

    @Override
    public List<Appointment> getAllAppointmentsByListing(int id) {
        return appointmentRepo.findAllByListingId(id);
    }

    @Override
    public void updateAppointment(Appointment change) {
        appointmentRepo.save(change);
    }

    @Override
    public boolean deleteAppointment(int id) {
        appointmentRepo.deleteById(id);
        return true;
    }
}
