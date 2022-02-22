package com.revature.services;

import com.revature.models.Appointment;

import java.util.List;

public interface AppointmentService {

    public Appointment addAppointment(Appointment a);
    public Appointment getAppointmentById(int id);
    public List<Appointment> getAllAppointmentsByClient(int id);
    public List<Appointment> getAllAppointmentsByListing(int id);
    public void updateAppointment(Appointment change);
    public boolean deleteAppointment(int id);

}
