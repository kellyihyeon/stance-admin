package com.github.kellyihyeon.stanceadmin.application.eventapplicant;

import com.github.kellyihyeon.stanceadmin.application.eventapplicant.dto.EventApplicantCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.EventApplicant;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.EventApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventApplicantService {

    private final EventApplicantRepository eventApplicantRepository;

    public void createEventApplicant(EventApplicantCreation eventApplicantCreation) {
        List<EventApplicant> eventApplicants = EventApplicantMapper.toDomains(eventApplicantCreation);

        for (EventApplicant eventApplicant : eventApplicants) {
            eventApplicantRepository.createEventApplicant(eventApplicant);
        }
    }
}
