package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Event;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class BookingController_updateEvent_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        LocalDate now = LocalDate.now();

        // When
        bookingController.updateEvent(3, "title", now);

        // Then
        verify(bookingFacade).updateEvent(Event.builder().id(3).title("title").date(now).build());
    }

    @Test
    public void shouldReturnPersistedEvent() {
        // Given
        LocalDate now = LocalDate.now();
        Event persistedEvent = Event.builder().title("title2").date(now).build();

        when(bookingFacade.updateEvent(any())).thenReturn(persistedEvent);
        // When
        ResponseEntity<Event> response = bookingController.updateEvent(3, "title", now);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(Event.builder().title("title2").date(now).build());
    }
}
