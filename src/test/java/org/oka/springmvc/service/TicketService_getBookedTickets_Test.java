package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.TicketRepository;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TicketService_getBookedTickets_Test {
    @InjectMocks
    TicketService ticketService;
    @Mock
    TicketRepository ticketRepository;

    @Test
    public void shouldCallRepository() {
        // Given
        User user = User.builder().name("Oscar").email("oscar@domain.com").build();

        // When
        ticketService.getBookedTickets(user, 66, 99);

        // Then
        verify(ticketRepository).findByUser(user, PageRequest.of(99, 66));
    }
}
