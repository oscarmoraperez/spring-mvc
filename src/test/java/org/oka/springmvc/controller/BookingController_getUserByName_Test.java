package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.User;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingController_getUserByName_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        Model model = mock(Model.class);

        // When
        bookingController.getUserByName("harry", 33, 55, model);

        // Then
        verify(bookingFacade).getUsersByName("harry", 33, 55);
    }

    @Test
    public void shouldSetModel() {
        // Given
        Model model = mock(Model.class);
        User user = User.builder().name("John").build();

        when(bookingFacade.getUsersByName("john", 33, 55)).thenReturn(List.of(user));
        // When
        bookingController.getUserByName("john", 33, 55, model);

        // Then
        verify(model).addAttribute("users", List.of(user));
    }

    @Test
    public void shouldViewName() {
        // Given
        Model model = mock(Model.class);

        // When
        String view = bookingController.getUserByName("Josep", 55, 33, model);

        // Then
        assertThat(view).isEqualTo("users");
    }
}
