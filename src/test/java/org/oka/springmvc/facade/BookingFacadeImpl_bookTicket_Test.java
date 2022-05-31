package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.service.EventService;
import org.oka.springmvc.service.TicketService;
import org.oka.springmvc.service.UserService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springmvc.model.Ticket.Category.PREMIUM;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_bookTicket_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacade;
    @Mock
    TicketService ticketService;
    @Mock
    UserService userService;
    @Mock
    EventService eventService;

    @Test
    public void shouldCallTicketService() {
        // Given
        User user = new User(1, "Karl", "karl@dom.com");
        Event event = new Event(1, "Hamlet", LocalDate.now());

        when(userService.getUserById(user.getId())).thenReturn(user);
        when(eventService.getEventById(event.getId())).thenReturn(event);
        // When
        bookingFacade.bookTicket(user.getId(), event.getId(), 66, PREMIUM);

        // Then
        verify(ticketService).bookTicket(user, event, 66, PREMIUM);
    }

    @Test
    public void shouldReturnTicket() {
        // Given
        User user = new User(1, "Karl", "karl@dom.com");
        Event event = new Event(1, "Hamlet", LocalDate.now());
        Ticket persistedTicket = Ticket.builder().user(user).event(event).place(66).category(PREMIUM).build();

        when(userService.getUserById(user.getId())).thenReturn(user);
        when(eventService.getEventById(event.getId())).thenReturn(event);
        when(ticketService.bookTicket(user, event, 66, PREMIUM)).thenReturn(persistedTicket);
        // When
        Ticket ticket = bookingFacade.bookTicket(1, 1, 66, PREMIUM);

        // Then
        assertThat(ticket.getUser()).isEqualTo(user);
        assertThat(ticket.getEvent()).isEqualTo(event);
        assertThat(ticket.getPlace()).isEqualTo(66);
        assertThat(ticket.getCategory()).isEqualTo(PREMIUM);
    }
}
