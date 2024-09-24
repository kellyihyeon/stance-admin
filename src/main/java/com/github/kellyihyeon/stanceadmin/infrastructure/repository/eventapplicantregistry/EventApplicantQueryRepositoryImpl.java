package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventApplicantProjection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventApplicantQueryRepositoryImpl implements EventApplicantQueryRepository {

    @Override
    public List<EventApplicantProjection> findApplicantsByEventId(Long eventId) {
        return null;
    }

}
