package com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.DepositRegistryData;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRegistry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MembershipFeeDepositRegistryMapperImpl implements MembershipFeeDepositRegistryMapper {

    @Override
    public List<MembershipFeeDepositRegistry> toDomains(List<DepositRegistryData> registryDataDto) {
        List<MembershipFeeDepositRegistry> result = new ArrayList<>();

        for (DepositRegistryData registryData : registryDataDto) {
            result.add(MembershipFeeDepositRegistry.create(
                    registryData.getMemberId(),
                    registryData.getMemberName(),
                    registryData.getAmount(),
                    registryData.getMemberStatus(),
                    registryData.getDueDate(),
                    registryData.getDepositDate()
            ));

        }

        return result;
    }
}
