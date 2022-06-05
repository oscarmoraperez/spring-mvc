package org.oka.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.repository.EventRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Event getEventById(final long eventId) {
        log.info("Retrieving event by id: " + eventId);

        return eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found (byId: " + eventId + ")"));
    }

    public List<Event> getEventsByTitle(final String title, final int pageSize, final int pageNum) {
        log.info("Retrieving events by title: " + title);

        return eventRepository.findByTitle(title, PageRequest.of(pageNum, pageSize));
    }

    public List<Event> getEventsForDay(final LocalDate day, final int pageSize, final int pageNum) {
        log.info("Retrieving events by day: " + day);

        return eventRepository.findByDate(day, PageRequest.of(pageNum, pageSize));
    }

    public Event createEvent(final Event event) {
        log.info("Creating event: " + event);

        return eventRepository.save(event);
    }

    public Event updateEvent(final Event event) {
        log.info("Updating event: " + event);

        return eventRepository.save(event);
    }

    public boolean deleteEvent(final long eventId) {
        log.info("Deleting event: " + eventId);

        boolean exists = eventRepository.existsById(eventId);
        if (exists) {
            eventRepository.deleteById(eventId);
        }

        return exists;
    }
}
