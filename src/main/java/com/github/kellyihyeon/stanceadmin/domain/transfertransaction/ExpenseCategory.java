package com.github.kellyihyeon.stanceadmin.domain.transfertransaction;

import lombok.Getter;

@Getter
public enum ExpenseCategory {
    BEVERAGE_PURCHASE("음료 구매비"),
    RENTAL_FEE("대관비"),
    LESSON_FEE("레슨비"),
    FRIENDLY_GAME_EXPENSE("교류전비"),
    COMPETITION_PARTICIPATION_FEE("대회 참가비"),
    UNIFORM_EXPENSE("유니폼 구매비"),
    TEAM_PARTY_EXPENSE("회식비");

    private String displayName;

    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }
}
