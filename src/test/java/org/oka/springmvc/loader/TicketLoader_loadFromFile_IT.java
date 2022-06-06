package org.oka.springmvc.loader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.TicketRepository;
import org.oka.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TicketLoader_loadFromFile_IT {
    @Value("classpath:tickets_w_2_entries.xml")
    Resource tickets_w_2_entries;
    @Value("classpath:tickets_w_errors.xml")
    Resource tickets_w_errors;
    @Autowired
    TicketLoader ticketLoader;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldSaveInTheDB() throws IOException {
        // Given

        // When
        ticketLoader.loadFromFile(tickets_w_2_entries.getFile());

        // Then
        User user = userRepository.findById(6L).orElseThrow();
        assertThat(ticketRepository.findByUser(user, PageRequest.of(0, 50))).hasSize(2);
    }

    @Test
    public void shouldRollbackEverything() {
        // Given
        long originalNumOfTickets = ticketRepository.count();

        // When
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> ticketLoader.loadFromFile(tickets_w_errors.getFile()));

        // Then
        assertThat(ticketRepository.count()).isEqualTo(originalNumOfTickets);
    }
}
