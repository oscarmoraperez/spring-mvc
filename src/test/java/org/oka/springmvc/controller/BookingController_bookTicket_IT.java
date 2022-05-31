package org.oka.springmvc.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.oka.springmvc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.oka.springmvc.model.Ticket.Category.PREMIUM;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_bookTicket_IT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void shouldBookTicket() throws Exception {
        // Given

        // When
        ResultActions resultActions = this.mockMvc
                .perform(post("/ticket")
                        .param("userId", "1")
                        .param("eventId", "2")
                        .param("place", "55")
                        .param("category", PREMIUM.name())
                        .header("content-type", "application/json"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk());
        // And
        long id = Long.parseLong(JsonPath.read(resultActions.andReturn().getResponse().getContentAsString(), "$.id").toString());
        // And
        assertThat(ticketRepository.findById(id)).isNotEmpty();
    }
}