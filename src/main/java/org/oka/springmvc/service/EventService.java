package org.oka.springmvc.service;

import lombok.extern.slf4j.Slf4j;
import org.oka.springmvc.dao.EventDAO;
import org.oka.springmvc.model.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class EventService {
    @Autowired
    private EventDAO eventDAO;

    public Event getEventById(final long eventId) {
        log.info("Retrieving event by id: " + eventId);
        return eventDAO.getById(eventId).orElseThrow(() -> new RuntimeException("Event not found (byId: " + eventId + ")"));
    }

    public List<Event> getEventsByTitle(final String title, final int pageSize, final int pageNum) {
        log.info("Retrieving events by title: " + title);
        return eventDAO.getByTitle(title, pageSize, pageNum);
    }

    public List<Event> getEventsForDay(final LocalDate day, final int pageSize, final int pageNum) {
        log.info("Retrieving events by day: " + day);
        return eventDAO.getByDate(day, pageSize, pageNum);
    }

    public Event createEvent(final Event event) {
        log.info("Creating event: " + event);
        return eventDAO.create(event);
    }

    public Event updateEvent(final Event event) {
        log.info("Updating event: " + event);
        return eventDAO.update(event);
    }

    public boolean deleteEvent(final long eventId) {
        log.info("Deleting event: " + eventId);
        return eventDAO.delete(eventId);
    }
}
