package com.example.demo.controller;

import com.example.demo.entity.Flight;
import com.example.demo.entity.Schedule;
import com.example.demo.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightSvc;

    public FlightController(FlightService service) {
        this.flightSvc = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> retrieveAllFlights(
            @RequestParam(name = "sortBy", required = false) String sortingCriteria) {
        List<Flight> flights = flightSvc.fetchAllFlights(sortingCriteria);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/details/{flightId}")
    public ResponseEntity<Flight> fetchFlightDetails(@PathVariable(name = "flightId") Long flightId) {
        Flight flight = flightSvc.retrieveFlight(flightId);
        return ResponseEntity.status(HttpStatus.OK).body(flight);
    }

    @GetMapping("/{flightId}/timetable")
    public ResponseEntity<List<Schedule>> fetchFlightTimetable(
            @PathVariable(name = "flightId") Long flightId,
            @RequestParam(name = "datetime", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime scheduleDateTime) {
        List<Schedule> schedules = flightSvc.fetchFlightSchedules(flightId, scheduleDateTime);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
}