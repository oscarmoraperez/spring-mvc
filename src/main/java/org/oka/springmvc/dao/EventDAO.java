package org.oka.springmvc.dao;

import lombok.Setter;
import org.oka.springmvc.db.EventDB;
import org.oka.springmvc.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Setter
public class EventDAO {

    private EventDB eventDB;
    private Pageable<Event> pageable;

    public Optional<Event> getById(final long id) {

        return eventDB.getEvents().stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public List<Event> getByTitle(final String title, final int pageSize, final int pageNum) {
        List<Event> events = eventDB.getEvents().stream()
                .filter(u -> u.getTitle().equals(title))
                .collect(toList());

        return pageable.paginate(events, pageSize, pageNum);
    }

    public List<Event> getByDate(final LocalDate day, final int pageSize, final int pageNum) {
        List<Event> events = eventDB.getEvents().stream()
                .filter(u -> u.getDate().equals(day))
                .collect(toList());

        return pageable.paginate(events, pageSize, pageNum);
    }

    public Event create(final Event event) {
        return eventDB.addEvent(event);
    }

    public Event update(final Event event) {
        Event eventToUpdate = eventDB.getEvents().stream()
                .filter(e -> e.getId() == event.getId())
                .findFirst()
                .orElseThrow();
        eventToUpdate.setTitle(event.getTitle());
        eventToUpdate.setDate(event.getDate());

        return event;
    }

    public boolean delete(final long eventId) {
        Optional<Event> eventToDelete = eventDB.getEvents().stream()
                .filter(e -> e.getId() == eventId)
                .findFirst();
        if (eventToDelete.isEmpty()) {
            return false;
        }

        return eventDB.getEvents().remove(eventToDelete.get());
    }
}
