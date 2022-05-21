package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oka.springmvc.db.UserDB;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-configuration.xml")
public class BookingFacadeImpl_deleteUser_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    UserDB userDB;

    @Test
    public void shouldDeleteUser() {
        // Given
        User user = UserImpl.builder().name("Peter").email("peter@domain.com").build();
        User persisted = bookingFacade.createUser(user);
        assertThat(userDB.getUsers()).contains(persisted);

        // When
        bookingFacade.deleteUser(persisted.getId());

        // Then
        assertThat(userDB.getUsers()).doesNotContain(persisted);
    }
}
