package com.revature.services;

import com.revature.models.Appointment;

import java.util.List;

public interface AppointmentService {

    public void addAppointment(Appointment a);
    public Appointment getAppointmentById(int id);
    public List<Appointment> getAllAppointments();
    public Appointment updateAppointment(Appointment change);
    public boolean deleteAppointment(int id);

}
