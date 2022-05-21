package org.oka.springmvc.db;

import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.EventImpl;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Serves as storage of Event objects.
 */
public class EventDB {
    /**
     * Simple structure to hold the events of the system (simulates a proper DB)
     **/
    private final List<Event> eventDb = new ArrayList<>();

    /**
     * Holds the incremental id (PK) of the Event object
     **/
    private final AtomicInteger index = new AtomicInteger(1);

    /**
     * Inits/populate the storage of Users.
     *
     * @throws IOException in case there are errors with location of the file.
     */
    public void initBean() throws IOException {
        List<String> events = Files.readAllLines(ResourceUtils.getFile("classpath:events.txt").toPath());
        for (String line : events) {
            String[] split = line.split(";");
            this.addEvent(EventImpl.builder().title(split[0]).date(LocalDate.parse(split[1])).build());
        }
    }

    /**
     * Returns the complete list of Event.
     *
     * @return list of Event
     */
    public List<Event> getEvents() {
        return eventDb;
    }

    /**
     * Adds an event to the repository
     *
     * @param event to add
     * @return event added (includes PK)
     */
    public Event addEvent(final Event event) {
        event.setId(this.index.addAndGet(1));
        eventDb.add(event);

        return event;
    }
}
