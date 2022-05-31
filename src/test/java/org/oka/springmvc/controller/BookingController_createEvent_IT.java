package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.oka.springmvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_createEvent_IT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void shouldCreateNewEvent() throws Exception {
        // Given

        // When
        ResultActions resultActions = this.mockMvc
                .perform(post("/event")
                        .param("title", "This is a new event!")
                        .param("date", LocalDate.now().toString()))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk());
        // And
        assertThat(eventRepository.findByTitle("This is a new event!", Pageable.ofSize(1))).isNotEmpty();
    }
}