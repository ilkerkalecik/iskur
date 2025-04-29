package com.iskur.demo.repository;

import com.iskur.demo.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByTcNo(String tcNo);
    Optional<Participant> findByStudentNo(String studentNo);
    boolean existsByTcNo(String tcNo);
    boolean existsByStudentNo(String studentNo);

    List<Participant> findByEventId(Long eventId);
}