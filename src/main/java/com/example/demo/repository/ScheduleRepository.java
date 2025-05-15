package com.example.demo.repository;

import com.example.demo.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Collection<Schedule> retrieveByFlightNumberAndDepartureAfter(Long flightNumber, LocalDateTime timestamp);

    List<Schedule> getByFlightAndTimeAfter(Long flightIdentifier, LocalDateTime referenceTime);
}