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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class BookingController_updateUser_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given

        // When
        bookingController.updateUser(3, "John", "john@domain.com");

        // Then
        verify(bookingFacade).updateUser(User.builder().id(3).name("John").email("john@domain.com").build());
    }

    @Test
    public void shouldReturnPersistedEvent() {
        // Given
        User persistedUser = User.builder().id(5).name("Harry").email("harry@domain.com").build();

        when(bookingFacade.updateUser(any())).thenReturn(persistedUser);
        // When
        ResponseEntity<User> response = bookingController.updateUser(5, "Harry", "harry@domain.com");

        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(User.builder().id(5).name("Harry").email("harry@domain.com").build());
    }
}
