package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookingFacadeImpl_deleteUser_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldDeleteUser() {
        // Given
        User user = User.builder().name("Peter").email("peter@domain.com").build();
        User persisted = bookingFacade.createUser(user);
        assertThat(userRepository.findById(persisted.getId())).isNotEmpty();

        // When
        bookingFacade.deleteUser(persisted.getId());

        // Then
        assertThat(userRepository.findById(persisted.getId())).isEmpty();
    }
}
