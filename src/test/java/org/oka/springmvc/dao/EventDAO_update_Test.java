package org.oka.springmvc.dao;

import org.junit.jupiter.api.Assertions;
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
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventDAO_update_Test {
    @InjectMocks
    EventDAO eventDAO;
    @Mock
    EventDB eventDB;

    @Test
    void shouldUpdateEvent() {
        // Given
        Event event = EventImpl.builder().id(1).title("title1").date(LocalDate.parse("2020-05-09")).build();
        Event event2 = EventImpl.builder().id(2).title("title1").date(LocalDate.parse("2020-05-09")).build();
        Event event3 = EventImpl.builder().id(1).title("title3").date(LocalDate.parse("2020-05-10")).build();
        List<Event> eventsMocked = new ArrayList<>();
        eventsMocked.add(event);
        eventsMocked.add(event2);

        when(eventDB.getEvents()).thenReturn(eventsMocked);
        // When
        Event actual = eventDAO.update(event3);

        // Then
        assertThat(actual).isEqualTo(event3);
    }

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        // Given
        Event event = EventImpl.builder().id(1).title("title1").date(LocalDate.parse("2020-05-09")).build();
        Event event2 = EventImpl.builder().id(2).title("title1").date(LocalDate.parse("2020-05-09")).build();
        Event event3 = EventImpl.builder().id(4).title("title3").date(LocalDate.parse("2020-05-10")).build();
        List<Event> eventsMocked = new ArrayList<>();
        eventsMocked.add(event);
        eventsMocked.add(event2);

        when(eventDB.getEvents()).thenReturn(eventsMocked);
        // When
        Assertions.assertThrows(NoSuchElementException.class, () -> eventDAO.update(event3));
    }
}
