package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.User;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingController_getUserByEmail_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        Model model = mock(Model.class);

        // When
        bookingController.getUserByEmail("harry@domain.com", model);

        // Then
        verify(bookingFacade).getUserByEmail("harry@domain.com");
    }

    @Test
    public void shouldSetModel() {
        // Given
        Model model = mock(Model.class);
        User user = User.builder().name("John").build();

        when(bookingFacade.getUserByEmail("john@domain.com")).thenReturn(user);
        // When
        bookingController.getUserByEmail("john@domain.com", model);

        // Then
        verify(model).addAttribute("user", user);
    }

    @Test
    public void shouldViewName() {
        // Given
        Model model = mock(Model.class);

        // When
        String view = bookingController.getUserByEmail("email@domain.com", model);

        // Then
        assertThat(view).isEqualTo("user");
    }
}
