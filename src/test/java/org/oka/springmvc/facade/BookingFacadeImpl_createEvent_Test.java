package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;
import org.oka.springmvc.service.EventService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_createEvent_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    EventService eventService;

    @Test
    void shouldCallEventService() {
        // Given
        Event event = EventImpl.builder().title("title").date(LocalDate.parse("2020-04-05")).build();

        // When
        bookingFacadeImpl.createEvent(event);

        // Then
        verify(eventService).createEvent(event);
    }

    @Test
    void shouldReturnEvent() {
        // Given
        Event event = EventImpl.builder().title("title").date(LocalDate.parse("2020-04-05")).build();
        Event persistedEvent = EventImpl.builder().id(15).title("title").date(LocalDate.parse("2020-04-05")).build();

        when(eventService.createEvent(event)).thenReturn(persistedEvent);
        // When
        Event persisted = bookingFacadeImpl.createEvent(event);

        // Then
        assertThat(persisted).isEqualTo(persistedEvent);
    }
}
