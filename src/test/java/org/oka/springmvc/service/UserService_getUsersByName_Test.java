package org.oka.springmvc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.dao.UserDAO;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserService_getUsersByName_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserDAO userDAO;

    @Test
    void shouldCallUserDAO() {
        // Given

        when(userDAO.getByName(anyString(), anyInt(), anyInt()))
                .thenReturn(List.of(new UserImpl(1, "Jose", "jose.canseco@aa.com")));
        // When
        userService.getUsersByName("name", 4, 6);

        // Then
        verify(userDAO).getByName("name", 4, 6);
    }

    @Test
    void shouldReturnUser() {
        // Given
        User user = new UserImpl(1, "Jose", "jose.canseco@aa.com");

        when(userDAO.getByName("Jose", 4, 6)).thenReturn(List.of(user));
        // When
        List<User> actual = userService.getUsersByName("Jose", 4, 6);

        // Then
        assertThat(actual).containsExactly(user);
    }
}
