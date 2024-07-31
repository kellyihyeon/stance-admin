package com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry;

import java.util.List;

public interface EventApplicantRegistryRepository {

    void createEventApplicant(EventApplicantRegistry eventApplicantRegistry);

    List<EventApplicantRegistry> getRegistriesByEventIdAndDepositorIds(Long eventId, List<Long> depositorIds);

    void saveAll(List<EventApplicantRegistry> eventApplicantRegistries);

}
