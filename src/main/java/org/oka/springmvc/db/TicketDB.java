package org.oka.springmvc.db;

import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.TicketImpl;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketDB {
    /**
     * Simple structure to hold the tickets of the system (simulates a proper DB)
     **/
    private final List<Ticket> ticketDb = new ArrayList<>();

    /**
     * Holds the incremental id (PK) of the Event object
     **/
    private final AtomicInteger index = new AtomicInteger(1);

    /**
     * Adds an ticket to the repository
     *
     * @param ticket to add
     * @return ticket added (includes PK)
     */
    public Ticket addTicket(final Ticket ticket) {
        ticket.setId(this.index.addAndGet(1));
        ticketDb.add(ticket);

        return ticket;
    }

    /**
     * Inits/populate the storage of Ticket.
     *
     * @throws IOException in case there are errors with location of the file.
     */
    public void initBean() throws IOException {
        List<String> tickets = Files.readAllLines(ResourceUtils.getFile("classpath:tickets.txt").toPath());
        for (String ticket : tickets) {
            String[] split = ticket.split(";");
            this.addTicket(TicketImpl.builder()
                    .userId(Long.parseLong(split[0]))
                    .place(Integer.parseInt(split[1]))
                    .eventId(Long.parseLong(split[2]))
                    .category(Ticket.Category.valueOf(split[3])).build());
        }
    }

    /**
     * Returns the complete list of Ticket.
     *
     * @return list of Tickt
     */
    public List<Ticket> getTickets() {
        return this.ticketDb;
    }
}
