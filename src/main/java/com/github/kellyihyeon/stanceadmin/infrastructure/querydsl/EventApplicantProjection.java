package com.github.kellyihyeon.stanceadmin.infrastructure.querydsl;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class EventApplicantProjection {

    private Long memberId;

    private String memberName;

    private String memo;

    private LocalDateTime createdAt;
}
