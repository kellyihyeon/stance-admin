package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaEventRepository extends JpaRepository<EventEntity, Long> {

    boolean existsByIdAndStatus(Long eventId, EventStatus eventStatus);

}
