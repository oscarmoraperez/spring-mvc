package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.UserRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserService_getUsersByName_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void shouldCallUserDAO() {
        // Given

        when(userRepository.findByName(anyString(), any()))
                .thenReturn(List.of(new User(1, "Jose", "jose.canseco@aa.com")));
        // When
        userService.getUsersByName("name", 6, 4);

        // Then
        verify(userRepository).findByName("name", PageRequest.of(4, 6));
    }

    @Test
    void shouldReturnUser() {
        // Given
        User user = new User(1, "Jose", "jose.canseco@aa.com");

        when(userRepository.findByName("Jose", PageRequest.of(4, 6))).thenReturn(List.of(user));
        // When
        List<User> actual = userService.getUsersByName("Jose", 6, 4);

        // Then
        assertThat(actual).containsExactly(user);
    }
}
