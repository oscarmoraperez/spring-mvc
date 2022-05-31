package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ExtendWith(MockitoExtension.class)
public class BookingController_deleteTicket_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        LocalDate now = LocalDate.now();

        // When
        bookingController.deleteTicket(55);

        // Then
        verify(bookingFacade).cancelTicket(55);
    }

    @Test
    public void shouldReturnNoContent() {
        // Given

        // When
        ResponseEntity<Void> response = bookingController.deleteTicket(45);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
    }
}
