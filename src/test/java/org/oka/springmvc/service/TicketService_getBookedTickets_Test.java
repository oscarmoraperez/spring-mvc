package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.dao.TicketDAO;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.TicketImpl;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springmvc.model.Ticket.Category.*;

@ExtendWith(MockitoExtension.class)
public class TicketService_getBookedTickets_Test {
    @InjectMocks
    TicketService ticketService;
    @Mock
    TicketDAO ticketDAO;

    @Test
    public void shouldCallDAO() {
        // Given
        User user = UserImpl.builder().name("Oscar").email("oscar@domain.com").build();

        // When
        ticketService.getBookedTickets(user, 66, 99);

        // Then
        verify(ticketDAO).getBookedTickets(user, 66, 99);
    }

    @Test
    public void shouldReturnTickets() {
        // Given
        User user = UserImpl.builder().name("Oscar").email("oscar@domain.com").build();
        Ticket ticket = TicketImpl.builder().userId(1).eventId(44).place(5).category(BAR).build();

        when(ticketDAO.getBookedTickets(user, 44, 22)).thenReturn(List.of(ticket));
        // When
        List<Ticket> actual = ticketService.getBookedTickets(user, 44, 22);

        // Then
        assertThat(actual).containsExactly(ticket);
    }
}
