package org.oka.springmvc.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserDB_addUser_Test {

    UserDB userDB = new UserDB();

    @Test
    public void shouldAddUser() {
        // Given
        User user = UserImpl.builder().name("a name").email("mail@domain.com").build();

        // When
        userDB.addUser(user);

        // Then
        assertThat(userDB.getUsers()).contains(user);
    }

    @Test
    public void shouldReturnUserWithId() {
        // Given
        User user = UserImpl.builder().name("a name").email("mail@domain.com").build();

        // When
        User persisted = userDB.addUser(user);

        // Then
        assertThat(persisted).isNotNull();
        assertThat(persisted.getId()).isGreaterThan(1);
    }
}
