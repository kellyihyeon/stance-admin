package com.github.kellyihyeon.stanceadmin.domain.withdraw;

public enum ExpenseCategory {
    BEVERAGE_PURCHASE("음료구입"),
    RENTAL_FEE("대관비"),
    LESSON_FEE("레슨비"),
    FRIENDLY_GAME_EXPENSE("교류전비"),
    COMPETITION_PARTICIPATION_FEE("대회참여비"),
    UNIFORM_EXPENSE("유니폼비"),
    TEAM_PARTY_EXPENSE("회식비");

    private final String displayName;

    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }
}
