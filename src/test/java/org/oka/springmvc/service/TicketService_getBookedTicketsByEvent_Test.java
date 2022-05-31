package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.TicketRepository;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springmvc.model.Ticket.Category.BAR;

@ExtendWith(MockitoExtension.class)
public class TicketService_getBookedTicketsByEvent_Test {
    @InjectMocks
    TicketService ticketService;
    @Mock
    TicketRepository ticketRepository;

    @Test
    public void shouldCallTicketRepository() {
        // Given
        Event event = Event.builder().title("title").date(LocalDate.now()).build();

        // When
        ticketService.getBookedTickets(event, 55, 99);

        // Then
        verify(ticketRepository).findByEvent(event, PageRequest.of(99, 55));
    }

    @Test
    public void shouldReturnTickets() {
        // Given
        Event event = Event.builder().title("title").date(LocalDate.now()).build();
        User user = User.builder().id(1).build();
        Ticket ticket = Ticket.builder().user(user).event(event).place(5).category(BAR).build();

        when(ticketRepository.findByEvent(event, PageRequest.of(22, 44))).thenReturn(List.of(ticket));
        // When
        List<Ticket> actual = ticketService.getBookedTickets(event, 44, 22);

        // Then
        assertThat(actual).containsExactly(ticket);
    }
}
