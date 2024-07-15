package com.github.kellyihyeon.stanceadmin.presentation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TimeConverter {

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
