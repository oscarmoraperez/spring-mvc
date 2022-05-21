package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.service.TicketService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_cancelTicket_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacade;
    @Mock
    TicketService ticketService;

    @Test
    public void shouldCallTicketService() {
        // Given

        // When
        bookingFacade.cancelTicket(1);

        // Then
        verify(ticketService).cancelTicket(1);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void shouldReturnCancelResult(final boolean returnValue) {
        // Given

        when(ticketService.cancelTicket(1)).thenReturn(returnValue);
        // When
        boolean result = bookingFacade.cancelTicket(1);

        // Then
        assertThat(result).isEqualTo(returnValue);
    }
}
