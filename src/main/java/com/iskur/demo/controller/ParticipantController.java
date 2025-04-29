package com.iskur.demo.controller;

import com.iskur.demo.entity.Participant;
import com.iskur.demo.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping("/{eventId}")
    public ResponseEntity<Participant> createParticipant(
            @RequestBody Participant participant,
            @PathVariable Long eventId) {
        Participant newParticipant = participantService.createParticipant(participant, eventId);
        return new ResponseEntity<>(newParticipant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        return ResponseEntity.ok(participantService.getAllParticipants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Participant>> getParticipantsByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(participantService.getParticipantsByEvent(eventId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(
            @PathVariable Long id,
            @RequestBody Participant participantDetails) {
        return ResponseEntity.ok(participantService.updateParticipant(id, participantDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}