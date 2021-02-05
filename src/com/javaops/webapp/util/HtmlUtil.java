package com.javaops.webapp.util;

import com.javaops.webapp.model.Organization;

public class HTMLUtil {
    public static String formatDates(Organization.Position position) {
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }
}
