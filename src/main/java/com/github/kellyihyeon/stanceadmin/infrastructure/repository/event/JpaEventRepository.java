package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.event.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JpaEventRepository extends JpaRepository<EventEntity, Long> {

    boolean existsByIdAndStatus(Long eventId, EventStatus eventStatus);

    List<EventEntity> findByStatusOrderByDueDate(EventStatus eventStatus);

}
