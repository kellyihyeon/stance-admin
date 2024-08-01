package com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry;

import java.util.List;

public interface EventApplicantRegistryRepository {

    void saveEventApplicant(EventApplicantRegistry eventApplicantRegistry);

    List<EventApplicantRegistry> getRegistriesByEventIdAndDepositorIds(Long eventId, List<Long> depositorIds);

    void updateAll(List<EventApplicantRegistry> eventApplicantRegistries);

    List<EventApplicantDepositRegistry> getEventApplicantRegistriesByEventId(Long eventId);

}
