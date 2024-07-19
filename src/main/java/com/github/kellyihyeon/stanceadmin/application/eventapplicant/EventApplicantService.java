package com.github.kellyihyeon.stanceadmin.application.eventapplicant;

import com.github.kellyihyeon.stanceadmin.application.eventapplicant.dto.EventApplicantCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.EventApplicant;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.EventApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventApplicantService {

    private final EventApplicantRepository eventApplicantRepository;

    public void createEventApplicant(EventApplicantCreation eventApplicantCreation) {
        EventApplicant eventApplicant = EventApplicantMapper.toDomain(eventApplicantCreation);
        eventApplicantRepository.createEventApplicant(eventApplicant);
    }
}
