package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingController_getTicketByUser_Test {
    @InjectMocks
    BookingController bookingController;
    @Mock
    BookingFacade bookingFacade;

    @Test
    public void shouldCallBookingFacade_getUserById() {
        // Given
        User user = User.builder().id(5).name("Jon").build();
        Model model = mock(Model.class);

        // When
        bookingController.getTicketByUser(user.getId(), 2, 0, model);

        // Then
        verify(bookingFacade).getUserById(5L);
    }

    @Test
    public void shouldCallBookingFacade_getBookedTickets() {
        // Given
        User user = User.builder().id(5).name("Jon").build();
        Model model = mock(Model.class);

        when(bookingFacade.getUserById(anyLong())).thenReturn(user);
        // When
        bookingController.getTicketByUser(user.getId(), 2, 0, model);

        // Then
        verify(bookingFacade).getBookedTickets(user, 2, 0);
    }

    @Test
    public void shouldSetModel() {
        // Given
        User user = User.builder().id(5).name("Jon").build();
        Ticket ticket = Ticket.builder().id(5).user(user).category(Ticket.Category.BAR).build();
        Model model = mock(Model.class);

        when(bookingFacade.getUserById(anyLong())).thenReturn(user);
        when(bookingFacade.getBookedTickets(user, 2, 0)).thenReturn(List.of(ticket));
        // When
        bookingController.getTicketByUser(user.getId(), 2, 0, model);

        // Then
        verify(model).addAttribute("tickets", List.of(ticket));
    }

    @Test
    public void shouldReturnView() {
        // Given
        User user = User.builder().id(5).name("Jon").build();
        Model model = mock(Model.class);

        // When
        String view = bookingController.getTicketByUser(user.getId(), 2, 0, model);

        // Then
        assertThat(view).isEqualTo("tickets");
    }
}
