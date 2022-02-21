package com.revature.services;

import com.revature.app.RepmaApplication;
import com.revature.models.Appointment;
import com.revature.repositories.AppointmentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RepmaApplication.class)
@Transactional
public class AppointmentServiceTests {

    // @Autowired
    // AppointmentService appointmentService;
    //
    // @Autowired
    // AppointmentRepo appointmentRepo;
    //
    // @ParameterizedTest
    // @ValueSource(ints = {1, 2, 3})
    // void givenClientId_whenGetAllAppts_thenGetAllApptsFromDb(int id) {
    //     List<Appointment> appts = appointmentService.getAllAppointmentsByClient(id);
    //     assertFalse(appts.isEmpty());
    // }
    //
    // @ParameterizedTest
    // @ValueSource(ints = {1, 2, 3})
    // void givenListingId_whenGetAllAppts_thenGetAllApptsFromDb(int id) {
    //     List<Appointment> appts = appointmentService.getAllAppointmentsByListing(id);
    //     assertFalse(appts.isEmpty());
    // }
    //
    // @ParameterizedTest
    // @ValueSource(ints = {1, 2, 3})
    // void givenApptId_whenGetApptById_thenGetApptFromDb(int id) {
    //     Appointment appt = appointmentService.getAppointmentById(id);
    //
    //     assertNotNull(appt);
    //     assertNotEquals(0, appt.getApptDate());
    //     assertNotEquals(0, appt.getListingId());
    //     assertNotEquals(0, appt.getClientId());
    //     assertNotEquals(0, appt.getApptId());
    // }
    //
    // @Test
    // @Rollback
    // void givenAppt_whenAddAppt_thenAddApptToDb() {
    //     long date = System.currentTimeMillis();
    //     Appointment testAppt = new Appointment(
    //             0,
    //             date,
    //             1,
    //             2
    //     );
    //
    //     appointmentService.addAppointment(testAppt);
    //     assertNotEquals(0, testAppt.getApptId());
    // }
    //
    // @Test
    // @Rollback
    // void givenAppt_whenUpdateAppt_thenUpdateApptInDb() {
    //     long date = System.currentTimeMillis();
    //     Appointment newAppt = new Appointment(
    //             1,
    //             date,
    //             1,
    //             2
    //     );
    //
    //     Appointment oldAppt = appointmentService.getAppointmentById(newAppt.getApptId());
    //
    //     appointmentService.updateAppointment(newAppt);
    //     assertEquals(1, newAppt.getApptId());
    //     assertNotEquals(oldAppt, newAppt);
    // }
    //
    // @ParameterizedTest
    // @ValueSource(ints = {1, 2, 3})
    // @Rollback
    // void givenApptId_whenDeleteAppt_thenDeleteApptInDb(int id) {
    //     appointmentService.deleteAppointment(id);
    //     Optional<Appointment> output = appointmentRepo.findById(id);
    //     assertFalse(output.isPresent());
    // }
    //
    // @Test
    // @Rollback
    // void givenNullApptId_whenDeleteAppt_thenThrowException() {
    //     assertThrows(EmptyResultDataAccessException.class, () -> {
    //         int id = 0;
    //         appointmentService.deleteAppointment(id);
    //     });
    // }
}
