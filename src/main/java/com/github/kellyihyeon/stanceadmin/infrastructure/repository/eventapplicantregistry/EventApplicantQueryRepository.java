package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventApplicantProjection;

import java.util.List;

public interface EventApplicantQueryRepository {

    List<EventApplicantProjection> findApplicantsByEventId(Long eventId);

}
