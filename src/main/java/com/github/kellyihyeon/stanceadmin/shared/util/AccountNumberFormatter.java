package com.github.kellyihyeon.stanceadmin.shared.util;

import java.util.Objects;

public class AccountNumberFormatter {

    public static String removeHyphens(String accountNumber) {
        Objects.requireNonNull(accountNumber, "계좌번호가 null 이어선 안됩니다.");
        return accountNumber.replaceAll("-", "");
    }
}
