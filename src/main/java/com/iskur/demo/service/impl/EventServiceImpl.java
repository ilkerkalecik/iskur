package com.iskur.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iskur.demo.entity.Event;
import com.iskur.demo.repository.EventRepository;
import com.iskur.demo.service.EventService;
import com.iskur.demo.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        Event existing = getEventById(id);
        existing.setName(eventDetails.getName());
        existing.setDescription(eventDetails.getDescription());
        existing.setStartDate(eventDetails.getStartDate());
        existing.setEndDate(eventDetails.getEndDate());
        // Eğer katılımcı güncellemesi de gerekiyorsa buraya ekleyin
        return eventRepository.save(existing);
    }

    @Override
    public void deleteEvent(Long id) {
        Event existing = getEventById(id);
        eventRepository.delete(existing);
    }
}