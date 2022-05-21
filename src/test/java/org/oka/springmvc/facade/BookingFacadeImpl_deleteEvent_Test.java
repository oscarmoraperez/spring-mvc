package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.service.EventService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_deleteEvent_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    EventService eventService;

    @Test
    void shouldCallEventService() {
        // Given
        long eventId = 1;

        // When
        bookingFacadeImpl.deleteEvent(eventId);

        // Then
        verify(eventService).deleteEvent(eventId);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void shouldReturnTrue(final boolean input) {
        // Given
        long eventId = 1;

        when(eventService.deleteEvent(eventId)).thenReturn(input);
        // When
        boolean result = bookingFacadeImpl.deleteEvent(eventId);

        // Then
        assertThat(result).isEqualTo(input);
    }
}
