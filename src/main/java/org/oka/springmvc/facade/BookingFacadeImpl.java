package org.oka.springmvc.facade;

import lombok.RequiredArgsConstructor;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.service.EventService;
import org.oka.springmvc.service.TicketService;
import org.oka.springmvc.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookingFacadeImpl implements BookingFacade {
    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    @Override
    public Event getEventById(final long eventId) {
        return eventService.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(final String title, final int pageSize, final int pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(final LocalDate day, final int pageSize, final int pageNum) {
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(final Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(final Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(final long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(final long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(final String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(final String name, final int pageSize, final int pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(final User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(final User user) {
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(final long userId, final long eventId, final int place, final Ticket.Category category) {
        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId);

        return ticketService.bookTicket(user, event, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(final long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}
