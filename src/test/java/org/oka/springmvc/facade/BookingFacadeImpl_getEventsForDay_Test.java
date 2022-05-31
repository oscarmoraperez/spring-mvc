package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.service.EventService;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getEventsForDay_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    EventService eventService;

    @Test
    void shouldCallEventService() {
        // Given
        LocalDate date = LocalDate.parse("2020-05-05");
        int pageSize = 34;
        int pageNum = 22;

        // When
        bookingFacadeImpl.getEventsForDay(date, pageSize, pageNum);

        // Then
        verify(eventService).getEventsForDay(date, pageSize, pageNum);
    }

    @Test
    void shouldReturnEvents() {
        // Given
        LocalDate now = LocalDate.now();
        Event event = new Event(1, "title", now);

        when(eventService.getEventsForDay(now, 1, 23)).thenReturn(List.of(event));
        // When
        List<Event> eventsForDay = bookingFacadeImpl.getEventsForDay(now, 1, 23);

        // Then
        assertThat(eventsForDay).isEqualTo(List.of(event));
    }
}
