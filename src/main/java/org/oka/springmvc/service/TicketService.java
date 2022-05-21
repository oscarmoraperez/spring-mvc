package org.oka.springmvc.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.oka.springmvc.dao.TicketDAO;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.TicketImpl;
import org.oka.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
@Slf4j
public class TicketService {
    @Autowired
    private TicketDAO ticketDAO;

    public Ticket bookTicket(final long userId, final long eventId, final int place, final Ticket.Category category) {
        log.info("Booking ticket for userId: " + userId + ", eventId: " + eventId + ", place: " + place + ", category: " + category);
        TicketImpl ticket = TicketImpl.builder()
                .userId(userId)
                .eventId(eventId)
                .place(place)
                .category(category)
                .build();
        return ticketDAO.bookTicket(ticket);
    }

    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        log.info("Retrieving tickets by user: " + user);
        return ticketDAO.getBookedTickets(user, pageSize, pageNum);
    }

    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        log.info("Retrieving tickets by event: " + event);
        return ticketDAO.getBookedTickets(event, pageSize, pageNum);
    }

    public boolean cancelTicket(final long ticketId) {
        log.info("Cancelling ticket: " + ticketId);
        return ticketDAO.cancelTicket(ticketId);
    }
}
