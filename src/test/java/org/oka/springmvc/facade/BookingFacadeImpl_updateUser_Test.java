package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;
import org.oka.springmvc.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_updateUser_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    UserService userService;

    @Test
    void shouldCallUserService() {
        // Given
        User user = new UserImpl(1, "name", "email");

        // When
        bookingFacadeImpl.updateUser(user);

        // Then
        verify(userService).updateUser(user);
    }

    @Test
    void shouldReturnUser() {
        // Given
        User user = new UserImpl(1, "name", "email");

        when(userService.updateUser(user)).thenReturn(user);
        // When
        User actual = bookingFacadeImpl.updateUser(user);

        // Then
        assertThat(actual).isEqualTo(user);
    }
}
