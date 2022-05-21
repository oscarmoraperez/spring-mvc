package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.TicketImpl;
import org.oka.springmvc.service.TicketService;

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

    @Test
    public void shouldCallTicketService() {
        // Given

        // When
        bookingFacade.bookTicket(1, 33, 66, PREMIUM);

        // Then
        verify(ticketService).bookTicket(1, 33, 66, PREMIUM);
    }

    @Test
    public void shouldReturnTicket() {
        // Given
        Ticket persistedTicket = TicketImpl.builder().userId(1).eventId(33).place(66).category(PREMIUM).build();

        when(ticketService.bookTicket(1, 33, 66, PREMIUM)).thenReturn(persistedTicket);
        // When
        Ticket ticket = bookingFacade.bookTicket(1, 33, 66, PREMIUM);

        // Then
        assertThat(ticket.getUserId()).isEqualTo(1);
        assertThat(ticket.getEventId()).isEqualTo(33);
        assertThat(ticket.getPlace()).isEqualTo(66);
        assertThat(ticket.getCategory()).isEqualTo(PREMIUM);
    }
}
