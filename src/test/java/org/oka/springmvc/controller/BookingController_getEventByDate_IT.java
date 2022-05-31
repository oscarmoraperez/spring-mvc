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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_getEventByDate_IT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnEventsByDate() throws Exception {
        // Given

        // When
        ResultActions resultActions = this.mockMvc
                .perform(get("/eventsByDate")
                        .param("date", "2021-04-04")
                        .param("pageSize", "1")
                        .param("pageNum", "0")
                        .header("content-type", "text/html"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(xpath("//td[text()='Who is afraid of Virginia Wolf?']").exists());
    }
}