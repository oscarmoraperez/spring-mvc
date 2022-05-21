package org.oka.springmvc.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EventDB_addEvent_Test {

    EventDB eventDB = new EventDB();

    @Test
    public void shouldAddEvent() {
        // Given
        Event event = EventImpl.builder().title("a title").date(LocalDate.now()).build();

        // When
        eventDB.addEvent(event);

        // Then
        assertThat(eventDB.getEvents()).contains(event);
    }

    @Test
    public void shouldReturnEventWithId() {
        // Given
        Event event = EventImpl.builder().title("a title").date(LocalDate.now()).build();

        // When
        Event persisted = this.eventDB.addEvent(event);

        // Then
        assertThat(persisted).isNotNull();
        assertThat(persisted.getId()).isGreaterThan(1);
    }
}
