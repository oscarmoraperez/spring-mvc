package org.oka.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.oka.springmvc.facade.BookingFacade;
import org.oka.springmvc.model.Event;
import org.oka.springmvc.model.Ticket;
import org.oka.springmvc.model.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import static org.oka.springmvc.model.Ticket.Category;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequiredArgsConstructor
public class BookingController {
    private final BookingFacade bookingFacade;

    @GetMapping(value = "/event/{id}")
    public String getEventById(@PathVariable("id") final Long id, Model model) {
        Event eventById = bookingFacade.getEventById(id);
        model.addAttribute("event", eventById);
        return "event";
    }

    @GetMapping(value = "/event/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Event> getEventByIdAsJson(@PathVariable("id") final Long id) {
        Event eventById = bookingFacade.getEventById(id);
        return new ResponseEntity<>(eventById, OK);
    }

    @GetMapping(value = "/eventsByTitle", produces = {"text/html"})
    public String getEventByTitle(@RequestParam("title") final String title,
                                  @RequestParam("pageSize") final int pageSize,
                                  @RequestParam("pageNum") final int pageNum,
                                  Model model) {
        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping(value = "/eventsByTitle", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<List<Event>> getEventByTitleAsJson(@RequestParam("title") final String title,
                                                             @RequestParam("pageSize") final int pageSize,
                                                             @RequestParam("pageNum") final int pageNum) {
        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        return new ResponseEntity<>(events, OK);
    }

    @GetMapping(value = "/eventsByDate", produces = {"text/html"})
    public String getEventByDate(@RequestParam("date") @DateTimeFormat(iso = DATE) final LocalDate localDate,
                                 @RequestParam("pageSize") final int pageSize,
                                 @RequestParam("pageNum") final int pageNum,
                                 Model model) {
        List<Event> events = bookingFacade.getEventsForDay(localDate, pageSize, pageNum);
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping(value = "/eventsByDate", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<List<Event>> getEventByDateAsJson(@RequestParam("date") @DateTimeFormat(iso = DATE) final LocalDate localDate,
                                                            @RequestParam("pageSize") final int pageSize,
                                                            @RequestParam("pageNum") final int pageNum) {
        List<Event> events = bookingFacade.getEventsForDay(localDate, pageSize, pageNum);
        return new ResponseEntity<>(events, OK);
    }

    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestParam("title") String title,
                                             @RequestParam("date") @DateTimeFormat(iso = DATE) final LocalDate localDate) {
        Event event = Event.builder().title(title).date(localDate).build();
        Event createdEvent = bookingFacade.createEvent(event);
        return new ResponseEntity<>(createdEvent, OK);
    }

    @PatchMapping("/event")
    public ResponseEntity<Event> updateEvent(@RequestParam("id") long id,
                                             @RequestParam("title") String title,
                                             @RequestParam("date") @DateTimeFormat(iso = DATE) final LocalDate localDate) {
        Event event = new Event(id, title, localDate);
        Event updatedEvent = bookingFacade.updateEvent(event);
        return new ResponseEntity<>(updatedEvent, OK);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") long id) {
        bookingFacade.deleteEvent(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping(value = "/user/{id}", produces = {"text/html"})
    public String getUserById(@PathVariable("id") final Long id, Model model) {
        User userById = bookingFacade.getUserById(id);
        model.addAttribute("user", userById);
        return "user";
    }

    @GetMapping(value = "/user/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<User> getUserByIdAsJson(@PathVariable("id") final Long id) {
        User userById = bookingFacade.getUserById(id);
        return new ResponseEntity<>(userById, OK);
    }

    @GetMapping(value = "/usersByEmail", consumes = {"text/html"}, produces = {"text/html"})
    public String getUserByEmail(@RequestParam("email") final String email,
                                 Model model) {
        User user = bookingFacade.getUserByEmail(email);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/usersByEmail", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<User> getUserByEmailAsJson(@RequestParam("email") final String email) {
        User user = bookingFacade.getUserByEmail(email);
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping(value = "/usersByName", produces = {"text/html"})
    public String getUserByName(@RequestParam("name") final String name,
                                @RequestParam("pageSize") final int pageSize,
                                @RequestParam("pageNum") final int pageNum,
                                Model model) {
        List<User> users = bookingFacade.getUsersByName(name, pageSize, pageNum);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/usersByName", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<List<User>> getUserByNameAsJson(@RequestParam("name") final String name,
                                                          @RequestParam("pageSize") final int pageSize,
                                                          @RequestParam("pageNum") final int pageNum) {
        List<User> users = bookingFacade.getUsersByName(name, pageSize, pageNum);
        return new ResponseEntity<>(users, OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestParam("name") final String name,
                                           @RequestParam("email") final String email) {
        User user = User.builder().name(name).email(email).build();
        User createdUser = bookingFacade.createUser(user);
        return new ResponseEntity<>(createdUser, OK);
    }

    @PatchMapping("/user")
    public ResponseEntity<User> updateUser(@RequestParam("id") final long id,
                                           @RequestParam("name") final String name,
                                           @RequestParam("email") final String email) {
        User user = User.builder().id(id).name(name).email(email).build();
        User updatedUser = bookingFacade.updateUser(user);
        return new ResponseEntity<>(updatedUser, OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        bookingFacade.deleteUser(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @PostMapping(value = "/ticket", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Ticket> bookTicket(@RequestParam("userId") long userId,
                                             @RequestParam("eventId") long eventId,
                                             @RequestParam("place") int place,
                                             @RequestParam("category") String category) {
        Ticket ticket = bookingFacade.bookTicket(userId, eventId, place, Category.valueOf(category));
        return new ResponseEntity<>(ticket, OK);
    }

    @GetMapping(value = "/ticketsByUser", produces = {"text/html"})
    public String getTicketByUser(@RequestParam("userId") final long userId,
                                  @RequestParam("pageSize") final int pageSize,
                                  @RequestParam("pageNum") final int pageNum,
                                  Model model) {
        User user = bookingFacade.getUserById(userId);
        List<Ticket> tickets = bookingFacade.getBookedTickets(user, pageSize, pageNum);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping(value = "/ticketsByEvent", produces = {"text/html"})
    public String getTicketByEvent(@RequestParam("eventId") final long eventId,
                                   @RequestParam("pageSize") final int pageSize,
                                   @RequestParam("pageNum") final int pageNum,
                                   Model model) {
        Event event = bookingFacade.getEventById(eventId);
        List<Ticket> tickets = bookingFacade.getBookedTickets(event, pageSize, pageNum);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") long id) {
        bookingFacade.cancelTicket(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
