package org.oka.springmvc.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.oka.springmvc.service.PdfService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BookingControllerPDF {
    private final BookingFacade bookingFacade;
    private final PdfService pdfService;

    @GetMapping(value = "/ticketsByUser", consumes = {"application/pdf; charset=UTF-8"}, produces = {"application/pdf; charset=UTF-8"})
    public ResponseEntity<?> getTicketByUser(@RequestParam("userId") final long userId,
                                             @RequestParam("pageSize") final int pageSize,
                                             @RequestParam("pageNum") final int pageNum) throws DocumentException {
        User user = bookingFacade.getUserById(userId);
        List<Ticket> tickets = bookingFacade.getBookedTickets(user, pageSize, pageNum);
        ByteArrayOutputStream outputStream = pdfService.generatePdfContent("tickets", Map.of("tickets", tickets));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(outputStream.toByteArray());
    }
}
