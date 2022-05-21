package org.oka.springmvc.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.db.TicketDB;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.TicketImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springmvc.model.Ticket.Category.BAR;

@ExtendWith(MockitoExtension.class)
public class TicketDAO_cancelTicket_Test {
    @InjectMocks
    TicketDAO ticketDAO;
    @Mock
    TicketDB ticketDB;

    @Test
    void shouldDeleteTicket() {
        // Given
        TicketImpl ticket = TicketImpl.builder().id(1).userId(1).eventId(3).place(5).category(BAR).build();
        TicketImpl ticket1 = TicketImpl.builder().id(2).userId(3).eventId(3).place(10).category(BAR).build();

        List<Ticket> ticketsMocked = new ArrayList<>();
        ticketsMocked.add(ticket);
        ticketsMocked.add(ticket1);

        when(ticketDB.getTickets()).thenReturn(ticketsMocked);
        // When
        boolean actual = ticketDAO.cancelTicket(1);

        // Then
        assertThat(actual).isTrue();
    }

    @Test
    void shouldReturnFalse() {
        // Given
        TicketImpl ticket = TicketImpl.builder().id(1).userId(1).eventId(3).place(5).category(BAR).build();
        TicketImpl ticket1 = TicketImpl.builder().id(2).userId(3).eventId(3).place(10).category(BAR).build();

        List<Ticket> ticketsMocked = new ArrayList<>();
        ticketsMocked.add(ticket);
        ticketsMocked.add(ticket1);

        when(ticketDB.getTickets()).thenReturn(ticketsMocked);
        // When
        boolean actual = ticketDAO.cancelTicket(4);

        // Then
        assertThat(actual).isFalse();
    }
}
