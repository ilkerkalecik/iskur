package com.iskur.demo.service;

import com.iskur.demo.entity.Event;
import com.iskur.demo.entity.Participant;
import com.iskur.demo.exception.ResourceNotFoundException;
import com.iskur.demo.exception.DuplicateResourceException;
import com.iskur.demo.repository.EventRepository;
import com.iskur.demo.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;

    // Yeni Katılımcı Oluşturma
    @Transactional
    public Participant createParticipant(Participant participant, Long eventId) {
        // Benzersiz alan kontrolleri
        if(participantRepository.existsByTcNo(participant.getTcNo())) {
            throw new DuplicateResourceException("TC Kimlik No zaten kayıtlı");
        }

        if(participantRepository.existsByStudentNo(participant.getStudentNo())) {
            throw new DuplicateResourceException("Öğrenci No zaten kayıtlı");
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Etkinlik bulunamadı"));
        participant.setEvent(event);

        return participantRepository.save(participant);
    }

    // Tüm Katılımcıları Getir
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    // ID ile Katılımcı Getir
    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Katılımcı bulunamadı"));
    }

    // Etkinliğe Göre Katılımcıları Listele
    public List<Participant> getParticipantsByEvent(Long eventId) {
        return participantRepository.findByEventId(eventId);
    }

    // Katılımcı Güncelle
    @Transactional
    public Participant updateParticipant(Long id, Participant participantDetails) {
        Participant participant = getParticipantById(id);

        // Güncellenebilir alanlar
        participant.setName(participantDetails.getName());
        participant.setSurname(participantDetails.getSurname());
        participant.setUniversity(participantDetails.getUniversity());
        participant.setFaculty(participantDetails.getFaculty());
        participant.setDepartment(participantDetails.getDepartment());
        participant.setClassYear(participantDetails.getClassYear());
        participant.setFilePath(participantDetails.getFilePath());

        return participantRepository.save(participant);
    }

    // Katılımcı Sil
    @Transactional
    public void deleteParticipant(Long id) {
        Participant participant = getParticipantById(id);
        participantRepository.delete(participant);
    }
}