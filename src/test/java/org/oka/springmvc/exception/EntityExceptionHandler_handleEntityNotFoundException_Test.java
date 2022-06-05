package org.oka.springmvc.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EntityExceptionHandler_handleEntityNotFoundException_Test {
    @InjectMocks
    EntityExceptionHandler entityExceptionHandler;

    @Test
    public void shouldAddAttribute() {
        // Given
        WebRequest webRequest = mock(WebRequest.class);
        Model model = mock(Model.class);

        // When
        entityExceptionHandler.handleEntityNotFoundException(new RuntimeException("I am a message!"), webRequest, model);

        // Then
        verify(model).addAttribute("error", new ExceptionMessage("I am a message!"));
    }

    @Test
    public void shouldReturnView() {
        // Given
        WebRequest webRequest = mock(WebRequest.class);
        Model model = mock(Model.class);

        // When
        String view = entityExceptionHandler.handleEntityNotFoundException(new RuntimeException("I am a message!"), webRequest, model);

        // Then
        assertThat(view).isEqualTo("error");
    }
}
