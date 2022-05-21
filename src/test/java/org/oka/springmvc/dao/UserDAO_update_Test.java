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
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAO_update_Test {
    @InjectMocks
    UserDAO userDAO;
    @Mock
    UserDB userDB;

    @Test
    void shouldUpdateUser() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");
        User user2 = new UserImpl(2, "Jose", "jose@doe.com");
        User user3 = new UserImpl(1, "Doe", "doe@doe.com");
        List<User> usersMocked = new ArrayList<>();
        usersMocked.add(user);
        usersMocked.add(user2);

        when(userDB.getUsers()).thenReturn(usersMocked);
        // When
        User actual = userDAO.update(user3);

        // Then
        assertThat(actual).isEqualTo(new UserImpl(1, "Doe", "doe@doe.com"));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");
        User user2 = new UserImpl(2, "Jose", "jose@doe.com");
        User user3 = new UserImpl(4, "Doe", "doe@doe.com");
        List<User> usersMocked = new ArrayList<>();
        usersMocked.add(user);
        usersMocked.add(user2);

        when(userDB.getUsers()).thenReturn(usersMocked);
        // When
        assertThrows(NoSuchElementException.class, () -> userDAO.update(user3));
    }
}
