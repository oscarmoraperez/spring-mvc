package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;
import org.oka.springmvc.service.EventService;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getEventsByTitle_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    EventService eventService;

    @Test
    void shouldCallEventService() {
        // Given
        String title = "a title";
        int pageSize = 34;
        int pageNum = 22;

        // When
        bookingFacadeImpl.getEventsByTitle(title, pageSize, pageNum);

        // Then
        verify(eventService).getEventsByTitle(title, pageSize, pageNum);
    }

    @Test
    void shouldReturnEvents() {
        // Given
        Event event = new EventImpl(1, "title", now());

        when(eventService.getEventsByTitle("title", 1, 3)).thenReturn(List.of(event));
        // When
        List<Event> actual = bookingFacadeImpl.getEventsByTitle("title", 1, 3);

        // Then
        assertThat(actual).isEqualTo(List.of(event));
    }
}
