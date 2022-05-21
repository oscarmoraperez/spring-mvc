package org.oka.springmvc.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.db.EventDB;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventDAO_getByDate_Test {
    @InjectMocks
    EventDAO eventDAO;
    @Mock
    EventDB eventDB;
    @Mock
    Pageable<Event> pageable;

    @Test
    void shouldCallEventDB() {
        // Given

        // When
        eventDAO.getByDate(LocalDate.now(), 1, 1);

        // Then
        verify(eventDB).getEvents();
    }

    @Test
    void shouldCallPageable() {
        // Given
        Event event = new EventImpl(1, "title ", LocalDate.now());

        when(eventDB.getEvents()).thenReturn(List.of(event));
        // When
        eventDAO.getByDate(LocalDate.now(), 1, 5);

        // Then
        verify(pageable).paginate(List.of(event), 1, 5);
    }

    @Test
    void shouldReturnTickets() {
        // Given
        Event event = new EventImpl(1, "title ", LocalDate.now());

        when(eventDB.getEvents()).thenReturn(List.of(event));
        when(pageable.paginate(List.of(event), 1, 5)).thenReturn(List.of(event));
        // When
        List<Event> actual = eventDAO.getByDate(LocalDate.now(), 1, 5);

        // Then
        assertThat(actual).containsExactly(event);
    }
}
