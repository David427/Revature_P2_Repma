package com.revature.controllers;

import com.revature.models.Appointment;
import com.revature.models.Client;
import com.revature.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/{appt_id}")
    public ResponseEntity<Appointment> getAppt(@PathVariable("appt_id") Integer id){
        Appointment appointments = appointmentService.getAppointmentById(id);

        if (appointments.getListingId() != 0) return new ResponseEntity<Appointment>(appointments, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@Authorized
    @DeleteMapping(value = "/{appt_id}")
    public ResponseEntity<Client> deleteAppointment(@PathVariable("appt_id") Integer id){
        boolean check = appointmentService.deleteAppointment(id);
        return new ResponseEntity<>((check) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

}
