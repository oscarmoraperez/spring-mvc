package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.repository.EventRepository;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_updateEvent_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventRepository eventRepository;

    @Test
    void shouldCallEventDAO() {
        // Given
        Event event = Event.builder().title("title").date(now()).build();

        // When
        eventService.updateEvent(event);

        // Then
        verify(eventRepository).save(event);
    }

    @Test
    void shouldReturnEvent() {
        // Given
        Event event = Event.builder().title("title").date(now()).build();
        Event eventUpdated = Event.builder().id(45).title("title2").date(now().plusDays(55)).build();

        when(eventRepository.save(event)).thenReturn(eventUpdated);
        // When
        Event actualEvent = eventService.updateEvent(event);

        // Then
        assertThat(actualEvent).isEqualTo(eventUpdated);
    }
}
