package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.oka.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_createUser_IT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreateNewUser() throws Exception {
        // Given

        // When
        ResultActions resultActions = this.mockMvc
                .perform(post("/user")
                        .param("name", "User")
                        .param("email", "user@domain.com"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk());
        // And
        assertThat(userRepository.findByName("User", Pageable.ofSize(1))).isNotEmpty();
    }
}