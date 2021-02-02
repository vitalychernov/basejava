package com.javaops.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String format(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.equals(LocalDate.now()) ? "По настоящее время" : date.format(DATE_TIME_FORMATTER);
    }
}
