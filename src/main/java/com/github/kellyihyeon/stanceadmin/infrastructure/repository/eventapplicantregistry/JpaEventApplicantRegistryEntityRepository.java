package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEventApplicantRegistryEntityRepository extends JpaRepository<EventApplicantRegistryEntity, Long> {

    List<EventApplicantRegistryEntity> findByEventIdAndApplicantIdIn(Long eventId, List<Long> applicantIds);
}
