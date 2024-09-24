package com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.event.EventService;
import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventApplicantProjection;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry.EventApplicantQueryRepository;
import com.github.kellyihyeon.stanceadmin.models.EventApplicantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventApplicantQueryService {

    private final EventService eventService;
    private final EventApplicantQueryRepository queryRepository;

    public List<EventApplicantResponse> getApplicantsByEventId(Long eventId) {
        if (!eventService.existsActiveEvent(eventId)) {
            throw new IllegalArgumentException("존재하지 않는 이벤트예요.");
        }

        List<EventApplicantProjection> projections = queryRepository.findApplicantsByEventId(eventId);
        // convert projection to response dto!

        return null;
    }
}
