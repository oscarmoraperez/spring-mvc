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
public class BookingFacadeImpl_updateEvent_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    EventService eventService;

    @Test
    void shouldCallEventService() {
        // Given
        Event event = EventImpl.builder().title("title").date(LocalDate.now()).id(22).build();

        // When
        bookingFacadeImpl.updateEvent(event);

        // Then
        verify(eventService).updateEvent(event);
    }

    @Test
    void shouldReturnUpdatedEvent() {
        // Given
        Event event = EventImpl.builder().title("title").date(LocalDate.now()).id(22).build();
        Event eventToReturn = EventImpl.builder().title("title").date(LocalDate.now()).id(55).build();

        when(eventService.updateEvent(event)).thenReturn(eventToReturn);
        // When
        Event updatedEvent = bookingFacadeImpl.updateEvent(event);

        // Then
        assertThat(updatedEvent).isEqualTo(eventToReturn);
    }
}
