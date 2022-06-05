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
public class BookingController_getUserById_IT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnUser() throws Exception {
        // Given

        // When
        ResultActions resultActions = this.mockMvc
                .perform(get("/user/{id}", 2)
                        .header("content-type", "text/html"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(xpath("//p[text()='Jose']").exists());
    }

    @Test
    public void shouldReturnErrorWhenUserDoesNotExist() throws Exception {
        // Given

        // When
        ResultActions resultActions = this.mockMvc
                .perform(get("/user/{id}", 99)
                        .header("content-type", "text/html"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(xpath("//p[text()='User not found (byId: 99)']").exists());
    }
}