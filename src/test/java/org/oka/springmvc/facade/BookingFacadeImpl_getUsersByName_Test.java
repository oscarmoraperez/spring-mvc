package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.model.User;
import org.oka.springmvc.service.UserService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getUsersByName_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    UserService userService;

    @Test
    void shouldCallUserService() {
        // Given

        when(userService.getUsersByName(anyString(), anyInt(), anyInt()))
                .thenReturn(List.of(new User(1, "Jose", "jose.canseco@aa.com")));
        // When
        bookingFacadeImpl.getUsersByName("name", 4, 10);

        // Then
        verify(userService).getUsersByName("name", 4, 10);
    }
}
