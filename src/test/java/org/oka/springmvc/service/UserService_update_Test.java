package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.dao.UserDAO;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserService_update_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserDAO userDAO;

    @Test
    void shouldCallUserDAO() {
        // Given
        User user = new UserImpl(1, "name", "name@domain.com");

        // When
        userService.updateUser(user);

        // Then
        verify(userDAO).update(user);
    }

    @Test
    void shouldReturnUser() {
        // Given
        User user = new UserImpl(1, "name", "name@domain.com");

        when(userDAO.update(user)).thenReturn(user);
        // When
        User persisted = userService.updateUser(user);

        // Then
        assertThat(persisted).isEqualTo(user);
    }
}
