package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oka.springmvc.db.EventDB;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-configuration.xml")
public class BookingFacadeImpl_deleteEvent_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    EventDB eventDB;

    @Test
    public void shouldDeleteEvent() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();
        Event persisted = bookingFacade.createEvent(event);
        assertThat(eventDB.getEvents()).contains(event);

        // When
        bookingFacade.deleteEvent(persisted.getId());

        // Then
        assertThat(eventDB.getEvents()).doesNotContain(persisted);
    }
}
