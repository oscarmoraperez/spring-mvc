package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.repository.EventRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_deleteEvent_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventRepository eventRepository;

    @Test
    void shouldCallEventRepository_ExistsById() {
        // Given

        // When
        eventService.deleteEvent(1);

        // Then
        verify(eventRepository).existsById(1L);
    }

    @Test
    void shouldCallEventRepository_DeleteById() {
        // Given

        when(eventRepository.existsById(anyLong())).thenReturn(true);
        // When
        eventService.deleteEvent(1);

        // Then
        verify(eventRepository).deleteById(1L);
    }

    @Test
    void shouldReturnEventResultResult() {
        // Given

        when(eventRepository.existsById(1L)).thenReturn(true);
        // When
        boolean result = eventService.deleteEvent(1);

        // Then
        assertThat(result).isTrue();
    }
}
