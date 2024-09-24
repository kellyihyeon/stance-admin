package com.github.kellyihyeon.stanceadmin.infrastructure.querydsl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class EventApplicantProjection {

    private Long memberId;

    private String memberName;

    private String memo;

    private LocalDateTime createdAt;

    public EventApplicantProjection(Long memberId, String memberName, String memo, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memo = memo;
        this.createdAt = createdAt;
    }
}
