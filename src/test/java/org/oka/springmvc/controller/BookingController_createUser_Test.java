package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.User;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class BookingController_createUser_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        LocalDate now = LocalDate.now();

        // When
        bookingController.createUser("name", "email");

        // Then
        verify(bookingFacade).createUser(User.builder().name("name").email("email").build());
    }

    @Test
    public void shouldReturnPersistedUser() {
        // Given
        LocalDate now = LocalDate.now();
        User persistedUser = User.builder().name("name").email("email").build();

        when(bookingFacade.createUser(any())).thenReturn(persistedUser);
        // When
        ResponseEntity<User> response = bookingController.createUser("name", "email");

        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(User.builder().name("name").email("email").build());
    }
}
