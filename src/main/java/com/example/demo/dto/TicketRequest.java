package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    @NotNull(message = "Trip schedule ID cannot be null")
    private Long tripScheduleId;

    @NotEmpty(message = "Name of passenger must be provided")
    private String travelerName;

    public Long getTripScheduleId() {
        return tripScheduleId;
    }

    public void setTripScheduleId(Long tripScheduleId) {
        this.tripScheduleId = tripScheduleId;
    }

    public String getTravelerName() {
        return travelerName;
    }

    public void setTravelerName(String travelerName) {
        this.travelerName = travelerName;
    }
}