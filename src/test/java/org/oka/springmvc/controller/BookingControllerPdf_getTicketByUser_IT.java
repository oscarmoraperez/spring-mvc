package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerPdf_getTicketByUser_IT {
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
                        .header("content-type", "application/pdf"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"));
        String content = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(content.length()).isGreaterThan(1);
    }
}