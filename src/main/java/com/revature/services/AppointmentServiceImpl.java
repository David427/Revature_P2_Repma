package com.revature.services;

import com.revature.models.Appointment;
import com.revature.models.Client;
import com.revature.repositories.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentRepo appointmentRepo;

    @Override
    public void addAppointment(Appointment c) {
        appointmentRepo.save(c);
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentRepo.findById(id).orElse(new Appointment());
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return (List<Appointment>) appointmentRepo.findAll();
    }

    @Override
    public Appointment updateAppointment(Appointment change) {
        return appointmentRepo.save(change);
    }

    @Override
    public boolean deleteAppointment(int id) {
        try {
            appointmentRepo.deleteById(id);
            return true;
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
