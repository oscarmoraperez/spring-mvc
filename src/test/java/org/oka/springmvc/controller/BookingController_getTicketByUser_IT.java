package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_getTicketByUser_IT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnUser() throws Exception {
        // Given

        // When
        ResultActions resultActions = this.mockMvc
                .perform(get("/ticketsByUser")
                        .param("userId", "1")
                        .param("pageSize", "1")
                        .param("pageNum", "0")
                        .header("content-type", "text/html"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=ISO-8859-1"))
                .andExpect(xpath("//td[text()='John']").exists());
    }
}