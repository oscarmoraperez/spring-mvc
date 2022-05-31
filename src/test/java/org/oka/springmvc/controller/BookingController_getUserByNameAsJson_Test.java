package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class BookingController_getUserByNameAsJson_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        Model model = mock(Model.class);

        // When
        bookingController.getUserByNameAsJson("harry", 33, 55);

        // Then
        verify(bookingFacade).getUsersByName("harry", 33, 55);
    }

    @Test
    public void shouldReturnUsers() {
        // Given
        User user = User.builder().name("Josep").email("josep@domain.com").build();

        when(bookingFacade.getUsersByName("Josep", 55, 33)).thenReturn(List.of(user));
        // When
        ResponseEntity<List<User>> response = bookingController.getUserByNameAsJson("Josep", 55, 33);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(List.of(user));
    }
}
