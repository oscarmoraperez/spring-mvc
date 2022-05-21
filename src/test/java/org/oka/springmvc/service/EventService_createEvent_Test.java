package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.dao.EventDAO;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;

import static java.time.LocalDate.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_createEvent_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventDAO eventDAO;

    @Test
    void shouldCallEventDAO() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();

        // When
        eventService.createEvent(event);

        // Then
        verify(eventDAO).create(event);
    }

    @Test
    void shouldReturnEvent() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();
        Event eventPersisted = EventImpl.builder().id(45).title("title").date(now()).build();

        when(eventDAO.create(event)).thenReturn(eventPersisted);
        // When
        Event actualEvent = eventService.createEvent(event);

        // Then
        assertThat(actualEvent).isEqualTo(eventPersisted);
    }
}
