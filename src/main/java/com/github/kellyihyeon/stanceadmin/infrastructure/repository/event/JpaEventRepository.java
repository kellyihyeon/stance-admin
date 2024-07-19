package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEventRepository extends JpaRepository<EventEntity, Long> {
}
