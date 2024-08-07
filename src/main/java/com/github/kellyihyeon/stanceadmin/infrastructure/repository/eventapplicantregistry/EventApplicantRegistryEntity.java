package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.DepositStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Table(name = "event_applicant_registry")
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

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updater_id")
    private Long updaterId;


    public EventApplicantRegistryEntity(Long eventId, Long applicantId, String description, DepositStatus depositStatus, LocalDateTime createdAt, Long creatorId) {
        this.eventId = eventId;
        this.applicantId = applicantId;
        this.description = description;
        this.depositStatus = depositStatus;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }
    private EventApplicantRegistryEntity(Long id, Long eventId, Long applicantId, String description, DepositStatus depositStatus, LocalDateTime createdAt, Long creatorId, LocalDateTime updatedAt, Long updaterId) {
        this.id = id;
        this.eventId = eventId;
        this.applicantId = applicantId;
        this.description = description;
        this.depositStatus = depositStatus;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
        this.updatedAt = updatedAt;
        this.updaterId = updaterId;
    }

    public static EventApplicantRegistryEntity createWithAllFields(Long id, Long eventId, Long applicantId, String description, DepositStatus depositStatus, LocalDateTime createdAt, Long creatorId, LocalDateTime updatedAt, Long updaterId) {
        return new EventApplicantRegistryEntity(
                id,
                eventId,
                applicantId,
                description,
                depositStatus,
                createdAt,
                creatorId,
                updatedAt,
                updaterId
        );
    }
}

