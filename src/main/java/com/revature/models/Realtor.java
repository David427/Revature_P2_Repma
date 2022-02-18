package com.revature.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

//region LOMBOK
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//endregion
@Entity
@Table(name = "realtors")
public class Realtor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "realtor_id")
    private int realtorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private String password;

    private String company;

    @OneToMany(mappedBy = "realtor")
    @JsonManagedReference
    private Set<Listing> managedListings;
}
