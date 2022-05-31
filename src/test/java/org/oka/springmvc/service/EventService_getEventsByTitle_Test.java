package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.repository.EventRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_getEventsByTitle_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventRepository eventRepository;

    @Test
    public void shouldCallEventDAO() {
        // Given
        String title = "title";
        int pageNum = 22;
        int pageSize = 66;

        // When
        eventService.getEventsByTitle(title, pageSize, pageNum);

        // Then
        verify(eventRepository).findByTitle(title, PageRequest.of(pageNum, pageSize));
    }

    @Test
    public void shouldReturnListOfEvents() {
        // Given
        String title = "title";
        int pageNum = 22;
        int pageSize = 66;
        Event event = Event.builder().title("title").build();

        when(eventRepository.findByTitle(title, PageRequest.of(pageNum, pageSize))).thenReturn(List.of(event));
        // When
        List<Event> eventsByTitle = eventService.getEventsByTitle(title, pageSize, pageNum);

        // Then
        assertThat(eventsByTitle).containsAll(List.of(event));
    }
}
