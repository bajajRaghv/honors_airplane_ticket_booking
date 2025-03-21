package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ticket")
public class TravelPass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id")
    private Long identifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timetable_id", referencedColumnName = "id")
    private Timetable timetable;

    @Column(name = "traveler_name")
    private String travelerFullName;

    @Column(name = "seat_position")
    private String assignedSeat;

    @Column(name = "fare_amount")
    private BigDecimal cost;

    @Column(name = "ticket_state")
    @Enumerated(EnumType.STRING)
    private TicketStatus state;
}