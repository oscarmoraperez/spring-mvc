package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.EventRepository;
import org.oka.springmvc.repository.TicketRepository;
import org.oka.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_deleteTicket_IT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldDeleteTicket() throws Exception {
        // Given
        Event event = Event.builder().title("titile").date(LocalDate.now()).build();
        eventRepository.save(event);
        User user = User.builder().name("Pete").email("pete@email.com").build();
        userRepository.save(user);
        Ticket ticket = Ticket.builder().user(user).event(event).category(Ticket.Category.BAR).place(44).build();
        ticketRepository.save(ticket);

        // When
        ResultActions resultActions = this.mockMvc
                .perform(delete("/ticket/{id}", ticket.getId()))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isNoContent());
        // And
        assertThat(ticketRepository.findById(ticket.getId())).isEmpty();
    }
}