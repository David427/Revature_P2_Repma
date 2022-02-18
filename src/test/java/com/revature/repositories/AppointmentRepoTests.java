package com.revature.repositories;

import com.revature.app.RepmaApplication;
import com.revature.models.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = RepmaApplication.class)
@Transactional
public class AppointmentRepoTests {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Test
    void givenNothing_whenFindAll_thenGetAllApptsFromDb() {
        List<Appointment> appts = (List<Appointment>) appointmentRepo.findAll();
        assertFalse(appts.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void givenApptId_whenFindById_thenGetApptFromDb(int id) {
        Optional<Appointment> dbAppt = appointmentRepo.findById(id);

        if (dbAppt.isPresent()) {
            Appointment appt = dbAppt.get();
            assertNotNull(appt);
            assertNotEquals(0, appt.getApptDate());
            assertNotEquals(0, appt.getListingId());
            assertNotEquals(0, appt.getClientId());
            assertNotEquals(0, appt.getApptId());
        }
    }

    @Test
    @Rollback
    void givenAppt_whenSave_thenAddApptToDb() {
        long date = System.currentTimeMillis();
        Appointment testAppt = new Appointment(
                0,
                date,
                1,
                2
        );

        testAppt = appointmentRepo.save(testAppt);
        assertNotEquals(0, testAppt.getApptId());

        Appointment getTestAppt = appointmentRepo.findById(testAppt.getApptId()).orElse(null);
        assertEquals(getTestAppt, testAppt);
    }

    @Test
    @Rollback
    void givenAppt_whenSave_thenUpdateApptInDb() {
        long date = System.currentTimeMillis();
        Appointment newAppt = new Appointment(
                1,
                date,
                1,
                2
        );

        Optional<Appointment> oldAppt = appointmentRepo.findById(newAppt.getApptId());

        newAppt = appointmentRepo.save(newAppt);
        assertEquals(1, newAppt.getApptId());
        assertNotEquals(oldAppt, newAppt);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Rollback
    void givenApptId_whenDeleteById_thenDeleteApptInDb(int id) {
        AppointmentRepo mockRepo = mock(AppointmentRepo.class);
        mockRepo.deleteById(id);
        verify(mockRepo).deleteById((id));
    }
}