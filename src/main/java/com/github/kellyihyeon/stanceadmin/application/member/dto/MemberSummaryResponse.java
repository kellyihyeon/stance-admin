package com.github.kellyihyeon.stanceadmin.application.member.dto;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberStatus;

public record MemberSummaryResponse(
        Long id,
        String name,
        MemberStatus memberStatus
) { }
