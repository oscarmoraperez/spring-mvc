package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.repository.EventRepository;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_getEventsForDay_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventRepository eventRepository;

    @Test
    public void shouldCallEventDAO() {
        // Given
        LocalDate day = parse("2020-05-09");
        int pageNum = 22;
        int pageSize = 66;

        // When
        eventService.getEventsForDay(day, pageSize, pageNum);

        // Then
        verify(eventRepository).findByDate(day, PageRequest.of(pageNum, pageSize));
    }

    @Test
    public void shouldReturnListOfEvents() {
        // Given
        LocalDate day = LocalDate.now();
        int pageNum = 22;
        int pageSize = 66;
        Event event = Event.builder().title("title").date(day).build();

        when(eventRepository.findByDate(day, PageRequest.of(pageNum, pageSize))).thenReturn(List.of(event));
        // When
        List<Event> events = eventService.getEventsForDay(day, pageSize, pageNum);

        // Then
        assertThat(events).containsAll(List.of(event));
    }
}
