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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_updateEvent_IT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void shouldUpdateNewEvent() throws Exception {
        // Given
        Event event = eventRepository.save(Event.builder().title("Old title").date(LocalDate.now()).build());

        // When
        ResultActions resultActions = this.mockMvc
                .perform(patch("/event")
                        .param("id", String.valueOf(event.getId()))
                        .param("title", "New title!")
                        .param("date", LocalDate.now().toString()))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk());
        // And
        assertThat(eventRepository.findById(event.getId()).orElseThrow().getTitle()).isEqualTo("New title!");
    }
}