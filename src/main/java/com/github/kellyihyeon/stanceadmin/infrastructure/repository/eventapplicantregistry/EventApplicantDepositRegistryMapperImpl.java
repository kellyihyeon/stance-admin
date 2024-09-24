package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.ApplicantRegistryData;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantDepositRegistry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventApplicantDepositRegistryMapperImpl implements EventApplicantDepositRegistryMapper {

    @Override
    public List<EventApplicantDepositRegistry> toDomains(List<ApplicantRegistryData> applicantRegistryDataDto) {
        List<EventApplicantDepositRegistry> result = new ArrayList<>();

        for (ApplicantRegistryData applicantRegistryData : applicantRegistryDataDto) {
            result.add(EventApplicantDepositRegistry.create(
                    applicantRegistryData.getEventDescription(),
                    applicantRegistryData.getDueDate(),
                    applicantRegistryData.getMemberName(),
                    applicantRegistryData.getAmount(),
                    applicantRegistryData.getDepositStatus(),
                    applicantRegistryData.getDepositDate()
            ));
        }

        return result;
    }

}
