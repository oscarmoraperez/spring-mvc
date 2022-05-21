package org.oka.springmvc.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.db.UserDB;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAO_delete_Test {
    @InjectMocks
    UserDAO userDAO;
    @Mock
    UserDB userDB;

    @Test
    void shouldDeleteUser() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");
        User user2 = new UserImpl(2, "Jose", "jose@doe.com");

        List<User> usersMocked = new ArrayList<>();
        usersMocked.add(user);
        usersMocked.add(user2);

        when(userDB.getUsers()).thenReturn(usersMocked);
        // When
        boolean actual = userDAO.delete(1);

        // Then
        assertThat(actual).isTrue();
    }

    @Test
    void shouldNotDeleteUser() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");
        User user2 = new UserImpl(2, "Jose", "jose@doe.com");
        User user3 = new UserImpl(3, "Jose2", "jose2@doe.com");

        List<User> usersMocked = new ArrayList<>();
        usersMocked.add(user);
        usersMocked.add(user2);

        when(userDB.getUsers()).thenReturn(usersMocked);
        // When
        boolean actual = userDAO.delete(3);

        // Then
        assertThat(actual).isFalse();
    }
}
