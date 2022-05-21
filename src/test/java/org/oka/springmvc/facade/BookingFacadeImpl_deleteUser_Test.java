package org.oka.springmvc.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springmvc.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_deleteUser_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    UserService userService;

    @Test
    void shouldCallUserService() {
        // Given
        long userId = 1;

        // When
        bookingFacadeImpl.deleteUser(userId);

        // Then
        verify(userService).deleteUser(userId);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void shouldReturnTrue(final boolean input) {
        // Given

        when(userService.deleteUser(1)).thenReturn(input);
        // When
        boolean result = bookingFacadeImpl.deleteUser(1);

        // Then
        assertThat(result).isEqualTo(input);
    }
}
