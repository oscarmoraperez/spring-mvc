package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.oka.springmvc.model.Ticket.Category.BAR;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = ANY)
public class BookingFacadeImpl_cancelTicket_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    TicketRepository ticketRepository;

    @Test
    public void shouldCancelBookedTicket() {
        // Given
        User user = User.builder().name("Peter").email("peter@domain.com").build();
        User persistedUser = bookingFacade.createUser(user);
        Event event = Event.builder().date(LocalDate.now()).title("King Lion").build();
        Event persistedEvent = bookingFacade.createEvent(event);
        Ticket persistedTicket = bookingFacade.bookTicket(persistedUser.getId(), persistedEvent.getId(), 55, BAR);

        // When
        bookingFacade.cancelTicket(persistedTicket.getId());

        // Then
        assertThat(ticketRepository.findById(persistedTicket.getId())).isEmpty();
    }
}
