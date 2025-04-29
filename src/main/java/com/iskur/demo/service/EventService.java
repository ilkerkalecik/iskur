package com.iskur.demo.service;

import com.iskur.demo.entity.Event;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(Long id);
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
}