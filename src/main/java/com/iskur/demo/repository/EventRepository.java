package com.iskur.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iskur.demo.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Ek sorgularınız olursa burada tanımlayabilirsiniz
}