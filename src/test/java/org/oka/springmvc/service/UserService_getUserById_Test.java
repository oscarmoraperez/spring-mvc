package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserService_getUserById_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void shouldCallUserDAO() {
        // Given
        long userId = 13;

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User(1, "Jose", "jose.canseco@aa.com")));
        // When
        userService.getUserById(userId);

        // Then
        verify(userRepository).findById(userId);
    }

    @Test
    void shouldReturnUser() {
        // Given
        long userId = 13;
        User user = new User(1, "Jose", "jose.canseco@aa.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        // When
        User actual = userService.getUserById(userId);

        // Then
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void shouldThrowException_WhenUserIsEmpty() {
        // Given
        long userId = 13;

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        // When
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> userService.getUserById(userId));

        // Then
        assertThat(runtimeException.getMessage()).isEqualTo("User not found (byId: 13)");
    }
}
