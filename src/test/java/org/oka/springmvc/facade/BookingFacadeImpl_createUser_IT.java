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
public class BookingFacadeImpl_createUser_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    UserDB userDB;

    @Test
    public void shouldCreateUser() {
        // Given
        User user = UserImpl.builder().name("Peter").email("peter@domain.com").build();

        // When
        User persisted = bookingFacade.createUser(user);

        // Then
        assertThat(userDB.getUsers()).contains(persisted);
    }
}
