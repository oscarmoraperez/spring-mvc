package org.oka.springmvc.db;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketDB_initBean_Test {

    @Test
    void shouldPopulate() throws IOException {
        // Given
        TicketDB ticketDB = new TicketDB();

        // When
        ticketDB.initBean();

        // Then
        assertThat(ticketDB.getTickets()).isNotEmpty();
    }
}
