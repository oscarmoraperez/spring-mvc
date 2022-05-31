package org.oka.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.TicketRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket bookTicket(final User user, final Event event, final int place, final Ticket.Category category) {
        log.info("Booking ticket for userId: " + user.getId() + ", eventId: " + event.getId() + ", place: " + place + ", category: " + category);
        Ticket ticket = Ticket.builder()
                .user(user)
                .event(event)
                .place(place)
                .category(category)
                .build();

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        log.info("Retrieving tickets by user: " + user);

        return ticketRepository.findByUser(user, PageRequest.of(pageNum, pageSize));
    }

    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        log.info("Retrieving tickets by event: " + event);

        return ticketRepository.findByEvent(event, PageRequest.of(pageNum, pageSize));
    }

    public boolean cancelTicket(final long ticketId) {
        log.info("Cancelling ticket: " + ticketId);

        boolean exists = ticketRepository.existsById(ticketId);
        if (exists) {
            ticketRepository.deleteById(ticketId);
        }

        return exists;
    }
}
