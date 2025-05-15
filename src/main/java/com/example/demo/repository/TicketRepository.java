package com.example.demo.repository;

import com.example.demo.entity.Ticket;
import com.example.demo.entity.TicketStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Optional<Ticket> retrieveByIdAndState(Long ticketId, TicketStatus ticketState);

    Optional<Ticket> findByIdAndStatus(Long ticketId, TicketStatus ticketStatus);
}