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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAO_getByName_Test {
    @InjectMocks
    UserDAO userDAO;
    @Mock
    UserDB userDB;
    @Mock
    Pageable<User> pageable;

    @Test
    void shouldCallUserDB() {
        // Given
        User user = new UserImpl(1, "Peter", "peter@dom.com");

        when(userDB.getUsers()).thenReturn(List.of(user));
        // When
        userDAO.getByName("non-existing", 5, 12);

        // Then
        verify(userDB).getUsers();
    }

    @Test
    void shouldCallPageable() {
        // Given
        User user1 = new UserImpl(1, "John", "johndoe@doe.com");
        User user2 = new UserImpl(2, "John", "johndoe2@doe.com");

        when(userDB.getUsers()).thenReturn(List.of(user1, user2));
        when(pageable.paginate(anyList(), anyInt(), anyInt())).thenReturn(List.of(user1));
        // When
        userDAO.getByName("John", 2, 0);

        // Then
        verify(pageable).paginate(List.of(user1, user2), 2, 0);
    }

    @Test
    void shouldReturnUsers() {
        // Given
        User user1 = new UserImpl(1, "John", "johndoe@doe.com");
        User user2 = new UserImpl(2, "John", "johndoe2@doe.com");

        when(userDB.getUsers()).thenReturn(List.of(user1, user2));
        when(pageable.paginate(anyList(), anyInt(), anyInt())).thenReturn(List.of(user1, user2));
        // When
        List<User> actual = userDAO.getByName("John", 2, 0);

        // Then
        assertThat(actual).containsExactly(user1, user2);
    }
}
