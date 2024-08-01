package com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.event.EventService;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.EventApplicantRegistryCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantDepositRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistryRepository;
import com.github.kellyihyeon.stanceadmin.models.EventApplicantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventApplicantRegistryService {

    private final EventApplicantRegistryRepository eventApplicantRegistryRepository;
    private final EventService eventService;

    private final EventApplicantRegistryMapper mapper;

    public void createEventApplicant(EventApplicantRegistryCreation eventApplicantRegistryCreation) {
        if (!eventService.existsActiveEvent(eventApplicantRegistryCreation.eventId())) {
            throw new IllegalArgumentException("존재하지 않는 이벤트예요.");
        }

        List<EventApplicantRegistry> eventApplicantRegistries = mapper.toDomains(eventApplicantRegistryCreation);

        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        for (EventApplicantRegistry eventApplicantRegistry : eventApplicantRegistries) {
            eventApplicantRegistryRepository.saveEventApplicant(
                    new EventApplicantRegistry(
                            eventApplicantRegistry.getEventId(),
                            eventApplicantRegistry.getApplicantId(),
                            eventApplicantRegistry.getDescription(),
                            eventApplicantRegistry.getDepositStatus(),
                            now,
                            loggedInId
                    )
            );
        }
    }

    public void processDepositCompletion(Long eventId, List<Long> depositorIds) {
        List<EventApplicantRegistry> eventApplicantRegistries = eventApplicantRegistryRepository.getRegistriesByEventIdAndDepositorIds(eventId, depositorIds);
        Long loggedInId = 999L;
        LocalDateTime updatedAt = LocalDateTime.now();

        for (EventApplicantRegistry registry : eventApplicantRegistries) {
            registry.updateDepositStatus(loggedInId, updatedAt);
        }

        eventApplicantRegistryRepository.updateAll(eventApplicantRegistries);
    }

    public List<EventApplicantResponse> getApplicantsForEvent(Long eventId) {
        if (!eventService.existsActiveEvent(eventId)) {
            throw new IllegalArgumentException("존재하지 않는 이벤트예요.");
        }

        List<EventApplicantDepositRegistry> registries = eventApplicantRegistryRepository.getEventApplicantRegistriesByEventId(eventId);

        return registries.stream()
                .map(
                        registry -> new EventApplicantResponse(
                                registry.getEventDescription(),
                                registry.getMemberName(),
                                registry.getAmount(),
                                registry.getDepositStatus().getDisplayName(),
                                registry.getDueDate().toString()
                        )
                )
                .collect(Collectors.toList());
    }
}
