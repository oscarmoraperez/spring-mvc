package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Event;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingController_getEventByDate_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade() {
        // Given
        Model model = mock(Model.class);
        LocalDate now = LocalDate.now();

        // When
        bookingController.getEventByDate(now, 2, 4, model);

        // Then
        verify(bookingFacade).getEventsForDay(now, 2, 4);
    }

    @Test
    public void shouldSetModel() {
        // Given
        Model model = mock(Model.class);
        Event event = Event.builder().title("title").build();
        LocalDate now = LocalDate.now();

        when(bookingFacade.getEventsForDay(any(), anyInt(), anyInt())).thenReturn(List.of(event));
        // When
        bookingController.getEventByDate(now, 4, 5, model);

        // Then
        verify(model).addAttribute("events", List.of(event));
    }

    @Test
    public void shouldViewName() {
        // Given
        Model model = mock(Model.class);

        // When
        String view = bookingController.getEventByDate(LocalDate.now(), 4, 5, model);

        // Then
        assertThat(view).isEqualTo("events");
    }
}
