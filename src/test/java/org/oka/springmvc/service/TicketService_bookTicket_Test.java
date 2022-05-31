package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.TicketRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springmvc.model.Ticket.Category.BAR;
import static org.oka.springmvc.model.Ticket.Category.PREMIUM;

@ExtendWith(MockitoExtension.class)
public class TicketService_bookTicket_Test {
    @InjectMocks
    TicketService ticketService;
    @Mock
    TicketRepository ticketRepository;

    @Test
    public void shouldCallDAO() {
        // Given
        User user = new User(1, "Peter", "peter@dom.com");
        Event event = new Event(1, "title", LocalDate.now());

        // When
        ticketService.bookTicket(user, event, 4, PREMIUM);

        // Then
        verify(ticketRepository).save(Ticket.builder().user(user).event(event).place(4).category(PREMIUM).build());
    }

    @Test
    public void shouldReturnTicket() {
        // Given
        User user = new User(1, "Peter", "peter@dom.com");
        Event event = new Event(1, "title", LocalDate.now());
        Ticket ticket = new Ticket(1, event, user, BAR, 44);

        when(ticketRepository.save(any())).thenReturn(ticket);
        // When
        Ticket actual = ticketService.bookTicket(user, event, 44, PREMIUM);

        // Then
        assertThat(actual).isEqualTo(ticket);
    }
}
