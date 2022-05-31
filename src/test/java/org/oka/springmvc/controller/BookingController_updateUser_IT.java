package org.oka.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingController_updateUser_IT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldUpdateEvent() throws Exception {
        // Given
        User user = userRepository.save(User.builder().name("Jon").email("jon@domain.com").build());

        // When
        ResultActions resultActions = this.mockMvc
                .perform(patch("/user")
                        .param("id", String.valueOf(user.getId()))
                        .param("name", "John")
                        .param("email", "john@domain.com"))
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().isOk());
        // And
        User persistedUser = userRepository.findById(user.getId()).orElseThrow();
        assertThat(persistedUser.getName()).isEqualTo("John");
        assertThat(persistedUser.getEmail()).isEqualTo("john@domain.com");
    }
}