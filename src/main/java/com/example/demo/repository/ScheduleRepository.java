package com.example.demo.data;

import com.example.demo.model.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface FlightScheduleDao extends JpaRepository<FlightSchedule, Long> {
    Collection<FlightSchedule> retrieveByFlightNumberAndDepartureAfter(Long flightNumber, LocalDateTime timestamp);
}