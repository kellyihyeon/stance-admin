package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaEventRepository extends JpaRepository<EventEntity, Long> {

    Optional<EventEntity> findByIdAndStatus(Long eventId, EventStatus status);

}
