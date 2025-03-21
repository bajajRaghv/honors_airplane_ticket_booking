package com.example.demo.repository;

import com.example.demo.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
    Collection<Flight> retrieveAllSortedByFlightNumberAscending();
}