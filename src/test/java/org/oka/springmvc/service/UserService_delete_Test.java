package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.UserRepository;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserService_delete_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void shouldCallUserRepository() {
        // Given
        User user = new User(1, "name", "name@domain.com");

        // When
        userService.deleteUser(1);

        // Then
        verify(userRepository).existsById(1L);
    }

    @Test
    void shouldNotCallUserRepository() {
        // Given
        User user = new User(1, "name", "name@domain.com");

        when(userRepository.existsById(anyLong())).thenReturn(false);
        // When
        userService.deleteUser(1);

        // Then
        verify(userRepository, never()).deleteById(1L);
    }
}
