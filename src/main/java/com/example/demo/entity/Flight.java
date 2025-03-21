package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class FlightDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;

    private String flightCode;
    private String departureCity;
    private String arrivalCity;

    @OneToMany(mappedBy = "associatedFlight")
    private Collection<ScheduleInfo> flightSchedules = new ArrayList<>();
}