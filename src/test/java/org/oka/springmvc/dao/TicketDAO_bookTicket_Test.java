package org.oka.springmvc.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.db.TicketDB;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.TicketImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springmvc.model.Ticket.Category.BAR;

@ExtendWith(MockitoExtension.class)
public class TicketDAO_bookTicket_Test {
    @InjectMocks
    TicketDAO ticketDAO;
    @Mock
    TicketDB ticketDB;

    @Test
    public void shouldCallDB() {
        // Given
        TicketImpl ticket = TicketImpl.builder().userId(1).eventId(3).place(5).category(BAR).build();

        // When
        ticketDAO.bookTicket(ticket);

        // Then
        verify(ticketDB).addTicket(ticket);
    }

    @Test
    public void shouldPersistedTicked() {
        // Given
        TicketImpl ticket = TicketImpl.builder().userId(1).eventId(3).place(5).category(BAR).build();
        TicketImpl persistedTicket = TicketImpl.builder().id(55).userId(1).eventId(3).place(5).category(BAR).build();

        when(ticketDAO.bookTicket(ticket)).thenReturn(persistedTicket);
        // When
        Ticket actual = ticketDAO.bookTicket(ticket);

        // Then
        assertThat(actual).isEqualTo(persistedTicket);
    }
}
