package com.github.kellyihyeon.stanceadmin.application.deposit.dto;

public record MembershipFeePaidMemberResponse(
        Long regularMembers,
        Long paidMembers,
        Long paidMembersPercentage
) { }
