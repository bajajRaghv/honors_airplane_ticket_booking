package com.example.demo.service;

import com.example.demo.entity.Flight;
import com.example.demo.entity.Schedule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightManager {
    private final FlightRepository flightRepo;
    private final ScheduleRepository scheduleRepo;

    public FlightManager(FlightRepository flightRepo, ScheduleRepository scheduleRepo) {
        this.flightRepo = flightRepo;
        this.scheduleRepo = scheduleRepo;
    }

    public List<Flight> retrieveAllFlights(String order) {
        if ("asc".equalsIgnoreCase(order)) {
            return flightRepo.fetchAllSortedByNumberAscending();
        }
        return flightRepo.retrieveAll();
    }

    public Flight fetchFlight(Long flightId) {
        return flightRepo.findByIdentifier(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("No flight exists with ID: " + flightId));
    }

    public List<Schedule> obtainFlightTimings(Long flightIdentifier, LocalDateTime timestamp) {
        LocalDateTime referenceTime = (timestamp != null) ? timestamp : LocalDateTime.now();
        return scheduleRepo.getByFlightAndTimeAfter(flightIdentifier, referenceTime);
    }
}