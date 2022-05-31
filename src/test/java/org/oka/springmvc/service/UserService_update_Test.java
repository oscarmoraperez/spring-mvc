package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserService_update_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void shouldCallUserDAO() {
        // Given
        User user = new User(1, "name", "name@domain.com");

        // When
        userService.updateUser(user);

        // Then
        verify(userRepository).save(user);
    }

    @Test
    void shouldReturnUser() {
        // Given
        User user = new User(1, "name", "name@domain.com");

        when(userRepository.save(user)).thenReturn(user);
        // When
        User persisted = userService.updateUser(user);

        // Then
        assertThat(persisted).isEqualTo(user);
    }
}
