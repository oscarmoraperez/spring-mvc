package org.oka.springmvc.loader;

import lombok.RequiredArgsConstructor;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.TicketsContainer;
import org.oka.springmvc.repository.TicketRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TicketLoader {
    private final TicketRepository ticketRepository;
    private final XMLManager xmlManager;
    private final JdbcTemplate jdbcTemplateObject;
    private final TransactionTemplate transactionTemplate;

    public void loadFromFile(final File file) throws IOException {

        TicketsContainer ticketsContainer = (TicketsContainer) xmlManager
                .convertFromXMLToObject(file.getAbsolutePath());

        this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    for (Ticket ticket : ticketsContainer.getTickets()) {
                        String sql = "INSERT INTO Ticket (category, place, event_id, user_id) VALUES (?, ?, ?, ?)";
                        jdbcTemplateObject.update(sql, ticket.getCategory().name(), ticket.getPlace(),
                                ticket.getEvent().getId(), ticket.getUser().getId());
                    }
                } catch (NoSuchElementException ex) {
                    status.setRollbackOnly();
                }
            }
        });
    }
}
