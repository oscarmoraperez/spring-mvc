package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.repository.EventRepository;
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
public class BookingController_deleteEvent_IT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void shouldDeleteEvent() throws Exception {
        // Given
        Event event = eventRepository.save(Event.builder().title("A title").date(LocalDate.now()).build());

        // When
        ResultActions resultActions = this.mockMvc
                .perform(delete("/event/{id}", event.getId()))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isNoContent());
        // And
        assertThat(eventRepository.findById(event.getId())).isEmpty();
    }
}