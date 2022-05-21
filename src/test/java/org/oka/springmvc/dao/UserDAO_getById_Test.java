package org.oka.springmvc.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.db.UserDB;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAO_getById_Test {
    @InjectMocks
    UserDAO userDAO;
    @Mock
    UserDB userDB;

    @Test
    void shouldReturnUser() {
        // Given
        long userId = 1;
        User user = new UserImpl(1, "John", "johndoe@doe.com");

        when(userDB.getUsers()).thenReturn(List.of(user));
        // When
        User actual = userDAO.getById(userId).orElseThrow();

        // Then
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void shouldReturnEmpty() {
        // Given
        long userId = 33;
        User user = new UserImpl(1, "John", "johndoe@doe.com");

        when(userDB.getUsers()).thenReturn(List.of(user));
        // When
        Optional<User> actual = userDAO.getById(userId);

        // Then
        assertThat(actual).isEmpty();
    }
}
