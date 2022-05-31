package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_createUser_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    UserService userService;

    @Test
    void shouldCallUserService() {
        // Given
        User user = new User(1, "name", "email");

        // When
        bookingFacadeImpl.createUser(user);

        // Then
        verify(userService).createUser(user);
    }

    @Test
    void shouldReturnUser() {
        // Given
        User user = new User(1, "name", "email");

        when(userService.createUser(user)).thenReturn(user);
        // When
        User actual = bookingFacadeImpl.createUser(user);

        // Then
        assertThat(actual).isEqualTo(user);
    }
}
