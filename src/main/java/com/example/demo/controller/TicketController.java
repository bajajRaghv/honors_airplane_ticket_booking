package com.example.demo.controller;


import com.example.demo.dto.TicketRequest;
import com.example.demo.entity.Ticket;
import com.example.demo.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketing")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public ResponseEntity<Ticket> addNewTicket(@Valid @RequestBody TicketRequest ticketData) {
        Ticket createdTicket = ticketService.generateTicket(ticketData);
        return ResponseEntity.status(201).body(createdTicket);
    }

    @GetMapping("/fetch/{ticketId}")
    public ResponseEntity<Ticket> retrieveTicket(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.fetchTicket(ticketId);
        return ResponseEntity.status(200).body(ticket);
    }

    @DeleteMapping("/remove/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.removeTicket(ticketId);
        return ResponseEntity.status(204).build();
    }
}