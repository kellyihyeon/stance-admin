package com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.DepositRegistryData;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRegistry;

import java.util.List;

public interface MembershipFeeDepositRegistryMapper {

    List<MembershipFeeDepositRegistry> toDomains(List<DepositRegistryData> registryDataDto);

}
