package com.github.kellyihyeon.stanceadmin.application.member.dto;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;

public record MemberSummaryResponse(
        Long id,
        String name,
        MemberType memberType
) { }
