package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "flight_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private Long identifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associated_flight_id")
    private Flight associatedFlight;

    @Column(name = "departure_datetime")
    private LocalDateTime takeoffTime;

    @Column(name = "arrival_datetime")
    private LocalDateTime landingTime;

    @Column(name = "seats_available")
    private Integer remainingSeats;

    public Schedule(Flight flight, LocalDateTime departure, LocalDateTime arrival, Integer seats) {
        this.associatedFlight = flight;
        this.takeoffTime = departure;
        this.landingTime = arrival;
        this.remainingSeats = seats;
    }

    public Integer getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(Integer remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
}