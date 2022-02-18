package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//region LOMBOK
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//endregion
@Entity
@Table(name = "appointments", schema = "project2")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appt_id")
    int apptId;

    @Column(name = "appt_date")
    long apptDate;

    @Column(name = "listing_id")
    int listingId;

    @Column(name = "client_id")
    int clientId;
}
