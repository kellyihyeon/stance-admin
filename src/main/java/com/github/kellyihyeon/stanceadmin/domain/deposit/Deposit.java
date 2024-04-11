package com.github.kellyihyeon.stanceadmin.domain.deposit;

import com.github.kellyihyeon.stanceadmin.application.deposit.dto.ExtraFee;
import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberIdAndName;
import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeForm;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.member.MembershipFeeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;


@Entity
@Getter
@ToString
@Table(name = "deposits")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private DepositCategory category;

    @Column(name = "depositor")
    private String depositor;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "member_type")
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Column(name = "membership_fee_type")
    @Enumerated(EnumType.STRING)
    private MembershipFeeType membershipFeeType;

    @Column(name = "due_month")
    @Enumerated(EnumType.STRING)
    private Month dueMonth;

    @Column(name = "deposit_date")
    private LocalDate depositDate;

    @Column(name = "description")
    private String description;

    private Long creatorId;

    private LocalDateTime createdDate;

    private Long updaterId;

    private LocalDateTime updatedDate;

    // TODO. 로그인 기능을 만들면 creatorId = 현재 로그인한 유저의 아이디로 수정
    private Deposit(MembershipFeeForm membershipFeeForm, MemberIdAndName memberIdAndName) {
        this.memberId = memberIdAndName.memberId();
        this.category = DepositCategory.MEMBERSHIP_FEE;
        this.depositor = memberIdAndName.memberName();
        this.amount = membershipFeeForm.getAmount();
        this.memberType = membershipFeeForm.getMemberType();
        this.membershipFeeType = membershipFeeForm.getMembershipFeeType();
        this.dueMonth = membershipFeeForm.getDueMonth();
        this.depositDate =LocalDate.parse(membershipFeeForm.getDepositDate());
        this.description = membershipFeeForm.getDescription();
        this.creatorId = 1L;
        this.createdDate = LocalDateTime.now();
    }

    public Deposit(ExtraFee extraFee, MemberIdAndName memberIdAndName) {
        this.memberId = memberIdAndName.memberId();
        this.category = extraFee.depositCategory();
        this.depositor = memberIdAndName.memberName();
        this.amount = extraFee.amount();
        this.depositDate = LocalDate.parse(extraFee.depositDate());
        this.description = extraFee.description();
        this.creatorId = 1L;
        this.createdDate = LocalDateTime.now();
    }


    public static List<Deposit> toEntityList(MembershipFeeForm membershipFeeForm) {
        List<Deposit> deposits = new LinkedList<>();
        membershipFeeForm.getMembers().forEach(
                memberIdAndName -> {
                    deposits.add(new Deposit(membershipFeeForm, memberIdAndName));
                }
        );

        return deposits;
    }

    public static List<Deposit> toEntityList(ExtraFee extraFee) {
        List<Deposit> deposits = new LinkedList<>();
        extraFee.members().forEach(
                memberIdAndName -> {
                    deposits.add(new Deposit(extraFee, memberIdAndName));
                }
        );

        return deposits;
    }
}
