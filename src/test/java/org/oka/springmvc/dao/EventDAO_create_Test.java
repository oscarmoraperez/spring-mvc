package org.oka.springmvc.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.db.EventDB;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventDAO_create_Test {
    @InjectMocks
    EventDAO eventDAO;
    @Mock
    EventDB eventDB;

    @Test
    void shouldCreateEvent() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();

        // When
        eventDAO.create(event);

        // Then
        verify(eventDB).addEvent(event);
    }

    @Test
    void shouldReturnEvent() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();
        Event eventToReturn = EventImpl.builder().id(55).title("title").date(now()).build();

        when(eventDB.addEvent(event)).thenReturn(eventToReturn);
        // When
        Event actual = eventDAO.create(event);

        // Then
        assertThat(actual).isEqualTo(eventToReturn);
    }
}
