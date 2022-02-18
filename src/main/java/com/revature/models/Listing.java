package com.revature.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

//region LOMBOK
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//endregion
@Entity
@Table(name = "listings", schema = "project2")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_id")
    private int listingId;

    private String address;

    private int bathrooms;

    private int bedrooms;

    @Column(name = "square_ft")
    private int squareFt;

    @Column(name = "year_built")
    private String yearBuilt;

    private double price;

    private double longitude;

    private double latitude;

    @Column(name = "realtor_id")
    private int realtorId;

    @ManyToOne
    @JoinColumn(name = "realtor_id", insertable = false, updatable = false)
    @JsonBackReference
    private Realtor realtor;

    @ManyToMany(mappedBy = "savedListings")
    @JsonIgnoreProperties("savedListings")
    @JsonIgnore
    private Set<Client> savedByClients;
}
