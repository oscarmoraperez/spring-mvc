package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.User;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class BookingController_getUserByEmailAsJson_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given

        // When
        bookingController.getUserByEmailAsJson("harry@domain.com");

        // Then
        verify(bookingFacade).getUserByEmail("harry@domain.com");
    }

    @Test
    public void shouldReturnUser() {
        // Given
        User user = User.builder().name("Jonah").email("jonah@domain.com").build();

        when(bookingFacade.getUserByEmail("jonah@domain.com")).thenReturn(user);
        // When
        ResponseEntity<User> response = bookingController.getUserByEmailAsJson("jonah@domain.com");

        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(user);
    }
}
