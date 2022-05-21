package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.dao.TicketDAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketService_cancelTicket_Test {
    @InjectMocks
    TicketService ticketService;
    @Mock
    TicketDAO ticketDAO;

    @Test
    public void shouldCallDAO() {
        // Given

        // When
        ticketService.cancelTicket(1);

        // Then
        verify(ticketDAO).cancelTicket(1);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void shouldReturnCancelResult(final boolean result) {
        // Given

        when(ticketDAO.cancelTicket(1)).thenReturn(result);
        // When
        boolean actual = ticketService.cancelTicket(1);

        // Then
        assertThat(actual).isEqualTo(result);
    }
}
