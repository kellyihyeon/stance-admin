package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.ApplicantRegistryData;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantDepositRegistry;

import java.util.List;

public interface EventApplicantDepositRegistryMapper {

    List<EventApplicantDepositRegistry> toDomains(List<ApplicantRegistryData> applicantRegistryDataDto);
}
