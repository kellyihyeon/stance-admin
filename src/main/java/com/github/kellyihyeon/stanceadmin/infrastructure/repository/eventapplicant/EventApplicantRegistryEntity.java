package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicant;

import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.DepositStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventApplicantRegistryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "applicant_id", nullable = false)
    private Long applicantId;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "deposit_status", nullable = false)
    private DepositStatus depositStatus;


    public EventApplicantRegistryEntity(Long eventId, Long applicantId, String description, DepositStatus depositStatus) {
        this.eventId = eventId;
        this.applicantId = applicantId;
        this.description = description;
        this.depositStatus = depositStatus;
    }
}

