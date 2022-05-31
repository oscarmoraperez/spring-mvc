package org.oka.springmvc.repository;

import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
    List<Ticket> findByUser(final User user, final Pageable pageable);

    List<Ticket> findByEvent(final Event event, final Pageable pageable);

    void deleteById(final long id);
}
