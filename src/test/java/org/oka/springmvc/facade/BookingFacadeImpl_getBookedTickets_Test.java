package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.User;
import org.oka.springmvc.service.TicketService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springmvc.model.Ticket.Category.BAR;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getBookedTickets_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacade;
    @Mock
    TicketService ticketService;

    @Test
    public void shouldCallTicketService() {
        // Given
        User user = User.builder().id(1).name("Jose").email("jose@domain.com").build();

        // When
        bookingFacade.getBookedTickets(user, 33, 44);

        // Then
        verify(ticketService).getBookedTickets(user, 33, 44);
    }

    @Test
    public void shouldReturnListOfTicket() {
        // Given
        User user = User.builder().id(1).name("Jose").email("jose@domain.com").build();
        Event event = Event.builder().id(1).title("title").build();
        Ticket ticket = Ticket.builder().user(user).event(event).place(66).category(BAR).build();

        when(ticketService.getBookedTickets(user, 33, 44)).thenReturn(List.of(ticket));
        // When
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(user, 33, 44);

        // Then
        assertThat(bookedTickets).contains(ticket);
    }
}
