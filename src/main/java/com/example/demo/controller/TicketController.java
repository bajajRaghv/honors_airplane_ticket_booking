package com.example.demo.controller;

import com.example.demo.dto.TicketInput;
import com.example.demo.entity.TicketEntity;
import com.example.demo.service.TicketManager;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketing")
public class TicketHandler {
    private final TicketManager ticketManager;

    public TicketHandler(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    @PostMapping("/create")
    public ResponseEntity<TicketEntity> addNewTicket(@Valid @RequestBody TicketInput ticketData) {
        TicketEntity createdTicket = ticketManager.generateTicket(ticketData);
        return ResponseEntity.status(201).body(createdTicket);
    }

    @GetMapping("/fetch/{ticketId}")
    public ResponseEntity<TicketEntity> retrieveTicket(@PathVariable Long ticketId) {
        TicketEntity ticket = ticketManager.fetchTicket(ticketId);
        return ResponseEntity.status(200).body(ticket);
    }

    @DeleteMapping("/remove/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketManager.removeTicket(ticketId);
        return ResponseEntity.status(204).build();
    }
}