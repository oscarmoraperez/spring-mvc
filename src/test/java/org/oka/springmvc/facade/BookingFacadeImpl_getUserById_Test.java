package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.service.UserService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getUserById_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    UserService userService;

    @Test
    void shouldCallUserService() {
        // Given
        long userId = 13;

        when(userService.getUserById(anyLong())).thenReturn(new User(1, "Jose", "jose.canseco@aa.com"));
        // When
        bookingFacadeImpl.getUserById(userId);

        // Then
        verify(userService).getUserById(13);
    }
}
