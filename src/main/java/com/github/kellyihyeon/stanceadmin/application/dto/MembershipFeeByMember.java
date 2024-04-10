package com.github.kellyihyeon.stanceadmin.application.dto;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.member.MembershipFeeType;
import lombok.Getter;

import java.time.Month;
import java.util.List;

@Getter
public class MembershipFeeByMember {

    private MemberType memberType;

    private MembershipFeeType membershipFeeType;

    private Long amount;

    private Month dueMonth;

    private String depositDate;

    private List<MemberIdAndName> members;

    private String description;
}
