package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.repository.TicketRepository;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketService_cancelTicket_Test {
    @InjectMocks
    TicketService ticketService;
    @Mock
    TicketRepository ticketRepository;

    @Test
    public void shouldNeverCallTicketRepository() {
        // Given

        when(ticketRepository.existsById(anyLong())).thenReturn(false);
        // When
        ticketService.cancelTicket(1);

        // Then
        verify(ticketRepository, never()).deleteById(1);
    }

    @Test
    public void shouldCallTicketRepository_DeleteById() {
        // Given

        when(ticketRepository.existsById(anyLong())).thenReturn(true);
        // When
        ticketService.cancelTicket(1);

        // Then
        verify(ticketRepository).deleteById(1);
    }
}
