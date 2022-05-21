package org.oka.springmvc.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.db.TicketDB;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.TicketImpl;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springmvc.model.Ticket.Category.BAR;

@ExtendWith(MockitoExtension.class)
public class TicketDAO_getBookedTickets_Test {
    @InjectMocks
    TicketDAO ticketDAO;
    @Mock
    TicketDB ticketDB;
    @Mock
    Pageable<Ticket> pageable;

    @Test
    void shouldCallTicketDB() {
        // Given
        User user = UserImpl.builder().id(8).name("Jose").build();

        // When
        ticketDAO.getBookedTickets(user, 1, 1);

        // Then
        verify(ticketDB).getTickets();
    }

    @Test
    void shouldCallPageable() {
        // Given
        User user = UserImpl.builder().id(1).name("Jose").build();
        Ticket ticket = new TicketImpl(1, 1, 1, BAR, 44);

        when(ticketDB.getTickets()).thenReturn(List.of(ticket));
        // When
        ticketDAO.getBookedTickets(user, 1, 5);

        // Then
        verify(pageable).paginate(List.of(ticket), 1, 5);
    }

    @Test
    void shouldReturnTickets() {
        // Given
        User user = UserImpl.builder().id(1).name("Jose").build();
        Ticket ticket = new TicketImpl(1, 1, 1, BAR, 44);

        when(ticketDB.getTickets()).thenReturn(List.of(ticket));
        when(pageable.paginate(List.of(ticket), 1, 5)).thenReturn(List.of(ticket));
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(user, 1, 5);

        // Then
        assertThat(actual).containsExactly(ticket);
    }
}
