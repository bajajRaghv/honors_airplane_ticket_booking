package com.example.demo.service;

import com.example.demo.dto.TicketRequest;
import com.example.demo.entity.Schedule;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.TicketStatus;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TicketBookingService {

    private final TicketRepository ticketRepo;
    private final ScheduleRepository scheduleRepo;

    @Autowired
    public TicketBookingService(TicketRepository ticketRepo, ScheduleRepository scheduleRepo) {
        this.ticketRepo = ticketRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @Transactional
    public Ticket bookNewTicket(TicketRequest request) {
        Optional<Schedule> scheduleOpt = scheduleRepo.findById(request.getScheduleId());
        if (!scheduleOpt.isPresent()) {
            throw new ResourceNotFoundException("No schedule exists with ID: " + request.getScheduleId());
        }

        Schedule currentSchedule = scheduleOpt.get();
        if (currentSchedule.getRemainingSeats() < 1) {
            throw new ValidationException("All seats are booked for this schedule");
        }

        Ticket newTicket = initializeTicket(request, currentSchedule);
        updateScheduleAvailability(currentSchedule);

        return ticketRepo.save(newTicket);
    }

    public Ticket fetchTicketDetails(Long ticketId) {
        Optional<Ticket> ticket = ticketRepo.findById(ticketId);
        return ticket.orElseThrow(() ->
                new ResourceNotFoundException("No ticket found for ID: " + ticketId));
    }

    @Transactional
    public void terminateTicket(Long ticketId) {
        Optional<Ticket> ticketOpt = ticketRepo.findByIdAndStatus(ticketId, TicketStatus.BOOKED);
        if (!ticketOpt.isPresent()) {
            throw new ValidationException("No active ticket found with ID: " + ticketId);
        }

        Ticket ticketToCancel = ticketOpt.get();
        processCancellation(ticketToCancel);
    }

    private Ticket initializeTicket(TicketRequest request, Schedule schedule) {
        Ticket ticket = new Ticket();
        ticket.setPassengerName(request.getPassengerName());
        ticket.setSchedule(schedule);
        ticket.setSeatAssignment(createSeatIdentifier());
        ticket.setTicketPrice(computeTicketCost(schedule));
        ticket.setBookingStatus(TicketStatus.BOOKED);
        return ticket;
    }

    private void updateScheduleAvailability(Schedule schedule) {
        int currentSeats = schedule.getRemainingSeats();
        schedule.setRemainingSeats(currentSeats - 1);
        scheduleRepo.save(schedule);
    }

    private void processCancellation(Ticket ticket) {
        ticket.setBookingStatus(TicketStatus.CANCELLED);
        Schedule associatedSchedule = ticket.getSchedule();
        associatedSchedule.setRemainingSeats(associatedSchedule.getRemainingSeats() + 1);

        ticketRepo.save(ticket);
        scheduleRepo.save(associatedSchedule);
    }

    private String createSeatIdentifier() {
        return "S" + String.format("%02d", (int)(Math.random() * 99) + 1);
    }

    private BigDecimal computeTicketCost(Schedule schedule) {
        return new BigDecimal("150.00");
    }
}