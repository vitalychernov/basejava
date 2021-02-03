package com.javaops.webapp.util;

import com.javaops.webapp.model.*;

import java.util.List;

public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(Organization.Position position) {
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }

    public static String getText(SectionType type, Resume resume) {
        AbstractSection section = resume.getSection(type);
        return section == null ? "" : ((TextSection) resume.getSection(type)).getText();
    }

    public static String getItems(SectionType type, Resume resume) {
        AbstractSection section = resume.getSection(type);
        return
                section == null ? "" :
                        String.join("\n", ((ListSection) resume.getSection(type)).getItems());
    }

    public static List<Organization> getOrganizations(SectionType type, Resume resume) {
        AbstractSection section = resume.getSection(type);
        return section == null ? null : ((OrganizationSection) resume.getSection(type)).getOrganizations();
    }
}
