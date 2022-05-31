package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Event;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingController_getEventById_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        Model model = mock(Model.class);

        // When
        bookingController.getEventById(1L, model);

        // Then
        verify(bookingFacade).getEventById(1L);
    }

    @Test
    public void shouldSetModel() {
        // Given
        Model model = mock(Model.class);
        Event event = Event.builder().title("title").build();

        when(bookingFacade.getEventById(anyLong())).thenReturn(event);
        // When
        bookingController.getEventById(1L, model);

        // Then
        verify(model).addAttribute("event", event);
    }

    @Test
    public void shouldViewName() {
        // Given
        Model model = mock(Model.class);

        // When
        String view = bookingController.getEventById(1L, model);

        // Then
        assertThat(view).isEqualTo("event");
    }
}
