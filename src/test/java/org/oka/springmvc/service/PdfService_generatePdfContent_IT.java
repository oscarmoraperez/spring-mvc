package org.oka.springmvc.service;

import com.lowagie.text.DocumentException;
import org.junit.jupiter.api.Test;
import org.oka.springmvc.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.oka.springmvc.model.Ticket.Category.BAR;

@SpringBootTest
public class PdfService_generatePdfContent_IT {
    @Autowired
    PdfService pdfService;

    @Test
    public void shouldCallTheTemplate() throws DocumentException {
        // Given
        Map<String, Object> data = Map.of("ticket", List.of(Ticket.builder().category(BAR)));

        // When
        ByteArrayOutputStream byteArrayOutputStream = pdfService.generatePdfContent("tickets", data);

        // Then
        assertThat(byteArrayOutputStream).isNotNull();
    }
}
