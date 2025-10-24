package com.example.demo.service;



import com.example.demo.dto.TicketPurchaseRequest;
import com.example.demo.dto.TicketStatusResponse;
import com.example.demo.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    private Map<Long, Ticket> ticketDB = new HashMap<>();
    private Long nextId = 1L;

    public TicketStatusResponse purchaseTicket(TicketPurchaseRequest request) {
        Ticket ticket = new Ticket(nextId++, request.getUserEmail(), request.getEventName(), "CONFIRMED");
        ticketDB.put(ticket.getId(), ticket);
        return new TicketStatusResponse(ticket.getId(), ticket.getEventName(), ticket.getStatus());
    }

    public TicketStatusResponse getTicketStatus(Long ticketId) {
        Ticket ticket = ticketDB.get(ticketId);
        if (ticket == null) {
            throw new NoSuchElementException("Ticket not found");
        }
        return new TicketStatusResponse(ticket.getId(), ticket.getEventName(), ticket.getStatus());
    }
}
