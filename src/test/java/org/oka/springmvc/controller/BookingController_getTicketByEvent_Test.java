package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingController_getTicketByEvent_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade_getEventById() {
        // Given
        Event event = Event.builder().id(6).title("title").build();
        Model model = mock(Model.class);

        // When
        bookingController.getTicketByEvent(event.getId(), 2, 0, model);

        // Then
        verify(bookingFacade).getEventById(event.getId());
    }

    @Test
    public void shouldCallBookingFacade_getBookedTickets() {
        // Given
        Event event = Event.builder().id(6).title("title").build();
        Model model = mock(Model.class);

        when(bookingFacade.getEventById(anyLong())).thenReturn(event);
        // When
        bookingController.getTicketByEvent(event.getId(), 2, 0, model);

        // Then
        verify(bookingFacade).getBookedTickets(event, 2, 0);
    }

    @Test
    public void shouldSetModel() {
        // Given
        Event event = Event.builder().id(6).title("title").build();
        Ticket ticket = Ticket.builder().id(5).event(event).category(Ticket.Category.BAR).build();
        Model model = mock(Model.class);

        when(bookingFacade.getEventById(anyLong())).thenReturn(event);
        when(bookingFacade.getBookedTickets(event, 2, 0)).thenReturn(List.of(ticket));
        // When
        bookingController.getTicketByEvent(event.getId(), 2, 0, model);

        // Then
        verify(model).addAttribute("tickets", List.of(ticket));
    }

    @Test
    public void shouldReturnView() {
        // Given
        Event event = Event.builder().id(6).title("title").build();
        Model model = mock(Model.class);

        // When
        String view = bookingController.getTicketByEvent(event.getId(), 2, 0, model);

        // Then
        assertThat(view).isEqualTo("tickets");
    }
}
