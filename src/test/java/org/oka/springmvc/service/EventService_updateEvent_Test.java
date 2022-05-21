package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.dao.EventDAO;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_updateEvent_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventDAO eventDAO;

    @Test
    void shouldCallEventDAO() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();

        // When
        eventService.updateEvent(event);

        // Then
        verify(eventDAO).update(event);
    }

    @Test
    void shouldReturnEvent() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();
        Event eventUpdated = EventImpl.builder().id(45).title("title2").date(now().plusDays(55)).build();

        when(eventDAO.update(event)).thenReturn(eventUpdated);
        // When
        Event actualEvent = eventService.updateEvent(event);

        // Then
        assertThat(actualEvent).isEqualTo(eventUpdated);
    }
}
