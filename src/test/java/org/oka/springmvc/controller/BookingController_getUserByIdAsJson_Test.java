package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
public class BookingController_getUserByIdAsJson_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given

        // When
        bookingController.getUserByIdAsJson(1L);

        // Then
        verify(bookingFacade).getUserById(1L);
    }

    @Test
    public void shouldReturnUser() {
        // Given
        User user = User.builder().name("John").build();

        when(bookingFacade.getUserById(anyLong())).thenReturn(user);
        // When
        ResponseEntity<User> user1 = bookingController.getUserByIdAsJson(1L);

        // Then
        assertThat(user1.getStatusCode()).isEqualTo(OK);
        assertThat(user1.getBody()).isEqualTo(user);
    }
}
