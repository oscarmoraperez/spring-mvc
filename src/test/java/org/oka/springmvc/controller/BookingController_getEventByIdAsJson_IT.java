package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_getEventByIdAsJson_IT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnEvent() throws Exception {
        // Given

        // When
        ResultActions resultActions = this.mockMvc
                .perform(get("/event/{id}", 2)
                        .header("content-type", "application/json"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("The King Lion")));
    }
}