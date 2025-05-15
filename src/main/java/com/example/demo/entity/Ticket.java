package com.example.demo.entity;

import com.example.demo.entity.TicketStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id")
    private Long identifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timetable_id", referencedColumnName = "id")
    private Schedule schedule;

    @Column(name = "passenger_name")
    private String travelerFullName;

    @Column(name = "seat_position")
    private String assignedSeat;

    @Column(name = "fare_amount")
    private BigDecimal cost;

    @Column(name = "ticket_state")
    @Enumerated(EnumType.STRING)
    private TicketStatus state;

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getTravelerFullName() {
        return travelerFullName;
    }

    public void setTravelerFullName(String travelerFullName) {
        this.travelerFullName = travelerFullName;
    }

    public String getAssignedSeat() {
        return assignedSeat;
    }

    public void setAssignedSeat(String assignedSeat) {
        this.assignedSeat = assignedSeat;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public TicketStatus getState() {
        return state;
    }

    public void setState(TicketStatus state) {
        this.state = state;
    }
}