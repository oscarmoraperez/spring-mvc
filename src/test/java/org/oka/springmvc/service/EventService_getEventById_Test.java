package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.dao.EventDAO;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_getEventById_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventDAO eventDAO;

    @Test
    void shouldCallEventDAO() {
        // Given
        long eventId = 13;

        when(eventDAO.getById(anyLong())).thenReturn(Optional.of(new EventImpl(1, "Jose", LocalDate.now())));
        // When
        eventService.getEventById(eventId);

        // Then
        verify(eventDAO).getById(eventId);
    }

    @Test
    void shouldReturnEvent() {
        // Given
        long eventId = 4;
        Event event = new EventImpl(1, "Jose", LocalDate.now());

        when(eventDAO.getById(anyLong())).thenReturn(Optional.of(event));
        // When
        Event actual = eventService.getEventById(eventId);

        // Then
        assertThat(actual).isEqualTo(event);
    }

    @Test
    void shouldThrowException_WhenUserIsEmpty() {
        // Given
        long eventId = 13;

        when(eventDAO.getById(anyLong())).thenReturn(Optional.empty());
        // When
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> eventService.getEventById(eventId));

        // Then
        assertThat(runtimeException.getMessage()).isEqualTo("Event not found (byId: 13)");
    }
}
