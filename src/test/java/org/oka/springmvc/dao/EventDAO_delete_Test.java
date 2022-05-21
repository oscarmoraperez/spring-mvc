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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventDAO_delete_Test {
    @InjectMocks
    EventDAO eventDAO;
    @Mock
    EventDB eventDB;

    @Test
    void shouldReturnTrue() {
        // Given
        Event event = new EventImpl(1, "title", LocalDate.parse("2022-05-05"));
        Event event2 = new EventImpl(2, "title2", LocalDate.parse("2020-05-09"));

        List<Event> eventsMocked = new ArrayList<>();
        eventsMocked.add(event);
        eventsMocked.add(event2);

        when(eventDB.getEvents()).thenReturn(eventsMocked);
        // When
        boolean actual = eventDAO.delete(1);

        // Then
        assertThat(actual).isTrue();
    }

    @Test
    void shouldReturnFalse() {
        // Given
        Event event = new EventImpl(1, "title", LocalDate.parse("2022-05-05"));
        Event event2 = new EventImpl(2, "title2", LocalDate.parse("2020-05-09"));

        List<Event> eventsMocked = new ArrayList<>();
        eventsMocked.add(event);
        eventsMocked.add(event2);

        when(eventDB.getEvents()).thenReturn(eventsMocked);
        // When
        boolean actual = eventDAO.delete(3);

        // Then
        assertThat(actual).isFalse();
    }
}
