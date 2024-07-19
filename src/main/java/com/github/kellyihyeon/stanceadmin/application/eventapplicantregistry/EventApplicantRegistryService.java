package com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.EventApplicantRegistryCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventApplicantRegistryService {

    private final EventApplicantRegistryRepository eventApplicantRegistryRepository;

    public void createEventApplicant(EventApplicantRegistryCreation eventApplicantRegistryCreation) {
        List<EventApplicantRegistry> eventApplicantRegistries = EventApplicantRegistryMapper.toDomains(eventApplicantRegistryCreation);

        for (EventApplicantRegistry eventApplicantRegistry : eventApplicantRegistries) {
            eventApplicantRegistryRepository.createEventApplicant(eventApplicantRegistry);
        }
    }
}
