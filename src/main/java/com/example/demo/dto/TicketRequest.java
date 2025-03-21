package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    @NotNull(message = "Trip schedule ID cannot be null")
    private Long tripScheduleId;

    @NotEmpty(message = "Name of passenger must be provided")
    private String travelerName;
}