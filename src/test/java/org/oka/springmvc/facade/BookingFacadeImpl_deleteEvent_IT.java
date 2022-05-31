package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookingFacadeImpl_deleteEvent_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    EventRepository eventRepository;

    @Test
    public void shouldDeleteEvent() {
        // Given
        Event event = Event.builder().title("title").date(now()).build();
        Event persisted = bookingFacade.createEvent(event);
        assertThat(eventRepository.findById(event.getId())).isNotEmpty();

        // When
        bookingFacade.deleteEvent(persisted.getId());

        // Then
        assertThat(eventRepository.findById(event.getId())).isEmpty();
    }
}
