package org.oka.springmvc.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.db.UserDB;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAO_create_Test {
    @InjectMocks
    UserDAO userDAO;
    @Mock
    UserDB userDB;

    @Test
    void shouldCallDB() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");

        // When
        userDAO.addUser(user);

        // Then
        verify(userDB).addUser(user);
    }

    @Test
    void shouldReturnPersistedUser() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");
        User userToReturn = new UserImpl(55, "John", "johndoe@doe.com");

        when(userDB.addUser(user)).thenReturn(userToReturn);
        // When
        User actual = userDAO.addUser(user);

        // Then
        assertThat(actual).isEqualTo(userToReturn);
    }
}
