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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserService_getUserByEmail_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void shouldCallUserDAO() {
        // Given
        String email = "name@domain.com";

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User(1, "Jose", "jose.canseco@aa.com")));
        // When
        userService.getUserByEmail(email);

        // Then
        verify(userRepository).findByEmail(email);
    }

    @Test
    void shouldReturnUser() {
        // Given
        String email = "jose.canseco@aa.com";
        User user = new User(1, "Jose", "jose.canseco@aa.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        // When
        User actual = userService.getUserByEmail(email);

        // Then
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void shouldThrowException_WhenUserIsEmpty() {
        // Given
        String email = "jose.canseco@aa.com";

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        // When
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> userService.getUserByEmail(email));

        // Then
        assertThat(runtimeException.getMessage()).isEqualTo("User not found (byEmail: " + email + ")");
    }
}
