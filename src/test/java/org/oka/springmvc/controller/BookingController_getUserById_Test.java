package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.User;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingController_getUserById_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        Model model = mock(Model.class);

        // When
        bookingController.getUserById(1L, model);

        // Then
        verify(bookingFacade).getUserById(1L);
    }

    @Test
    public void shouldSetModel() {
        // Given
        Model model = mock(Model.class);
        User user = User.builder().name("John").build();

        when(bookingFacade.getUserById(anyLong())).thenReturn(user);
        // When
        bookingController.getUserById(1L, model);

        // Then
        verify(model).addAttribute("user", user);
    }

    @Test
    public void shouldViewName() {
        // Given
        Model model = mock(Model.class);

        // When
        String view = bookingController.getUserById(1L, model);

        // Then
        assertThat(view).isEqualTo("user");
    }
}
