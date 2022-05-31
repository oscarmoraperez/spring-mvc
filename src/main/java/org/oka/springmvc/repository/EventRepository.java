package org.oka.springmvc.repository;

import org.oka.springmvc.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
    List<Event> findByTitle(final String title, final Pageable pageable);

    List<Event> findByDate(final LocalDate date, final Pageable pageable);
}
